package com.amazingenergy.saitamajpa.merchant.repository;

import com.amazingenergy.saitamaappservice.merchant.model.MerchantStoreCriteria;
import com.amazingenergy.saitamajpa.merchant.entity.MerchantStoreEntity;
import com.amazingenergy.saitamajpa.seedwork.RepositoryHelper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
public class CustomMerchantRepositoryImpl implements CustomMerchantStoreRepository {

    private final EntityManager em;

    public CustomMerchantRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Page<MerchantStoreEntity> listAll(MerchantStoreCriteria criteria) {
        return RepositoryHelper.paginateQuery(criteria,
                () -> queryByStore(criteria),
                () -> countByStore(criteria));
    }

    private TypedQuery<MerchantStoreEntity> queryByStore(MerchantStoreCriteria criteria) {
        var selectString = "select distinct m from MerchantStoreEntity m " +
                "left join fetch m.country mc " +
                "left join fetch m.parent cp " +
                "left join fetch m.currency mcr " +
                "left join fetch m.zone mz " +
                "left join fetch m.defaultLanguage md " +
                "left join fetch m.languages mls";

        selectString += " order by m." + criteria.getOrderBy() + " " + criteria.getDirection().name().toLowerCase();

        var query = this.em.createQuery(selectString, MerchantStoreEntity.class);

        return applyFilterParameters(query, criteria);
    }

    private TypedQuery<Integer> countByStore(MerchantStoreCriteria criteria) {
        var countString = "select count(distinct m) from MerchantStoreEntity m ";

        countString += getFilterString(criteria);

        var query = this.em.createQuery(countString, Integer.class);

        return applyFilterParameters(query, criteria);
    }

    private String getFilterString(MerchantStoreCriteria criteria) {
        var filterString = new StringBuilder(" where 1=1 ");

        if (criteria.getCode() != null) {
            filterString.append("  and lower(m.code) like:code");
        }

        if (criteria.getName() != null) {
            filterString.append(" or lower(m.storename) like:name");
        }

        return filterString.toString();
    }

    private <T> TypedQuery<T> applyFilterParameters(TypedQuery<T> query, MerchantStoreCriteria criteria) {
        if (criteria.getCode() != null) {
            query.setParameter("code", "%" + criteria.getCode().toLowerCase() + "%");
        }

        if (criteria.getName() != null) {
            query.setParameter("name", "%" + criteria.getCode().toLowerCase() + "%");
        }

        return query;
    }
}
