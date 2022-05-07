package com.amazingenergy.saitamajpa.catalog.product.repository;

import com.amazingenergy.saitamaappservice.catalog.product.model.ProductCriteria;
import com.amazingenergy.saitamadomain.seedwork.constant.Constants;
import com.amazingenergy.saitamajpa.catalog.product.entity.ProductEntity;
import com.amazingenergy.saitamajpa.seedwork.RepositoryHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.UUID;

@Repository
public class CustomProductRepositoryImpl implements CustomProductRepository {
    private final EntityManager em;

    public CustomProductRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Page<ProductEntity> listByStore(UUID storeId, String languageCode, ProductCriteria criteria) {
        return RepositoryHelper.paginateQuery(criteria,
                () -> queryByStore(storeId, languageCode, criteria),
                () -> countByStore(storeId, languageCode, criteria));
    }

    private TypedQuery<ProductEntity> queryByStore(UUID storeId, String languageCode, ProductCriteria criteria) {
        var queryString = "select distinct p from ProductEntity as p " +
                "join fetch p.merchantStore merch " +
                "join fetch p.availabilities pa " +
                "left join fetch pa.prices pap " +
                "join fetch p.descriptions pd " +
                // categories
                "left join fetch p.categories categs " +
                "left join fetch categs.descriptions cd " +
                // images
                "left join fetch p.images images " +
                // other lefts
                "left join fetch p.manufacturer manuf " +
                "left join fetch manuf.descriptions manufd " +
                "left join fetch p.type type " +
                "left join fetch p.taxClass tx " +
                // RENTAL
                "left join fetch p.owner owner ";

        // attributes
        if (!criteria.getAttributeCriteria().isEmpty()) {
            queryString += " inner join p.attributes pattr" +
            " inner join pattr.productOption po" +
            " inner join po.descriptions pod" +
            " inner join pattr.productOptionValue pov " +
            " inner join pov.descriptions povd";
        } else {
            queryString += " left join fetch p.attributes pattr" +
            " left join fetch pattr.productOption po" +
            " left join fetch po.descriptions pod" +
            " left join fetch pattr.productOptionValue pov" +
            " left join fetch pov.descriptions povd";
        }

        queryString += " left join fetch p.relationships pr";
        queryString += getFilterString(criteria);
        queryString += " order by p.sortOrder asc";

        var query = this.em.createQuery(queryString, ProductEntity.class);

        return applyFilterParameters(query, storeId, languageCode, criteria);
    }

    private TypedQuery<Integer> countByStore(UUID storeId, String languageCode, ProductCriteria criteria) {
        var selectString = "select count(distinct p) from ProductEntity as p" +
                " inner join p.descriptions pd" +
                " INNER JOIN p.categories categs" +
                " INNER JOIN p.manufacturer manuf" +
                " INNER JOIN p.owner owner" +
                " INNER JOIN p.attributes pattr" +
                " INNER JOIN pattr.option po" +
                " INNER JOIN pattr.optionValue pov " +
                " INNER JOIN pov.descriptions povd ";

        selectString += getFilterString(criteria);

        var query = this.em.createQuery(selectString, Integer.class);

        return applyFilterParameters(query, storeId, languageCode, criteria);
    }

    private String getFilterString(ProductCriteria criteria) {
        var filterString = new StringBuilder(" where merch.id=:mId");

        if (criteria.getLanguage() != null && !criteria.getLanguage().equals(Constants.ALL_LANGUAGES)) {
            filterString.append(" and pd.language.code=:lang");
        }

        if (!criteria.getProductIds().isEmpty()) {
            filterString.append(" and p.id in (:pId)");
        }

        if (!StringUtils.isBlank(criteria.getProductName())) {
            filterString.append(" and lower(pd.name) like:nm");
        }

        if (!criteria.getCategoryIds().isEmpty()) {
            filterString.append(" and categs.id in (:cid)");
        }

        if (criteria.getManufacturerId() != null) {
            filterString.append(" and manuf.id = :manufid");
        }

        if (!StringUtils.isBlank(criteria.getCode())) {
            filterString.append(" and lower(p.sku) like :sku");
        }

        // RENTAL
        if (!StringUtils.isBlank(criteria.getStatus())) {
            filterString.append(" and p.rentalStatus = :status");
        }

        if (criteria.getOwnerId() != null) {
            filterString.append(" and owner.id = :ownerid");
        }

        if (criteria.getAvailable() != null) {
            if (criteria.getAvailable()) {
                filterString.append(" and p.available=true and p.dateAvailable<=:dt");
            } else {
                filterString.append(" and p.available=false and p.dateAvailable>:dt");
            }
        }

        if (!criteria.getAttributeCriteria().isEmpty()) {
            int count = 0;
            for (var attributeCriteria : criteria.getAttributeCriteria()) {
                filterString.append(" and po.code =:").append(attributeCriteria.getAttributeCode());
                filterString.append(" and povd.description like :").append("val").append(count)
                        .append(attributeCriteria.getAttributeCode());
                count++;
            }
            if (criteria.getLanguage() != null && !criteria.getLanguage().equals(Constants.ALL_LANGUAGES)) {
                filterString.append(" and povd.language.code=:lang");
            }

        }

        if (!criteria.getOptionValueIds().isEmpty()) {
            filterString.append(" and pov.id in (:povid)");
        }

        return filterString.toString();
    }

    private <T> TypedQuery<T> applyFilterParameters(TypedQuery<T> query, UUID storeId, String languageCode, ProductCriteria criteria) {
        query.setParameter("mId", storeId);

        if (criteria.getLanguage() != null && !criteria.getLanguage().equals(Constants.ALL_LANGUAGES)) {
            query.setParameter("lang", languageCode);
        }

        if (!criteria.getCategoryIds().isEmpty()) {
            query.setParameter("cid", criteria.getCategoryIds());
        }

        if (!criteria.getOptionValueIds().isEmpty()) {
            query.setParameter("povid", criteria.getOptionValueIds());
        }

        if (!criteria.getProductIds().isEmpty()) {
            query.setParameter("pId", criteria.getProductIds());
        }

        if (criteria.getAvailable() != null) {
            query.setParameter("dt", new Date());
        }

        if (criteria.getManufacturerId() != null) {
            query.setParameter("manufid", criteria.getManufacturerId());
        }

        if (!StringUtils.isBlank(criteria.getCode())) {
            query.setParameter("sku",
                    new StringBuilder().append("%").append(criteria.getCode().toLowerCase()).append("%").toString());
        }

        if (!criteria.getAttributeCriteria().isEmpty()) {
            int cnt = 0;
            for (var attributeCriteria : criteria.getAttributeCriteria()) {
                query.setParameter(attributeCriteria.getAttributeCode(), attributeCriteria.getAttributeCode());
                query.setParameter("val" + cnt + attributeCriteria.getAttributeCode(),
                        "%" + attributeCriteria.getAttributeValue() + "%");
                cnt++;
            }
        }

        // RENTAL
        if (!StringUtils.isBlank(criteria.getStatus())) {
            query.setParameter("status", criteria.getStatus());
        }

        if (criteria.getOwnerId() != null) {
            query.setParameter("ownerid", criteria.getOwnerId());
        }

        if (!StringUtils.isBlank(criteria.getProductName())) {
            query.setParameter("nm", new StringBuilder().append("%").append(criteria.getProductName().toLowerCase())
                    .append("%").toString());
        }

        return query;
    }
}
