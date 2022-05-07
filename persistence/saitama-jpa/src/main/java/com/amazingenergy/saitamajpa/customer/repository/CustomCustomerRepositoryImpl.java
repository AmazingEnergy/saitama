package com.amazingenergy.saitamajpa.customer.repository;

import com.amazingenergy.saitamaappservice.customer.model.CustomerCriteria;
import com.amazingenergy.saitamajpa.customer.entity.CustomerEntity;
import com.amazingenergy.saitamajpa.seedwork.RepositoryHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.UUID;

@Repository
public class CustomCustomerRepositoryImpl implements CustomCustomerRepository {

    private final EntityManager em;

    public CustomCustomerRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Page<CustomerEntity> listByStore(UUID storeId, CustomerCriteria criteria) {
        return RepositoryHelper.paginateQuery(criteria,
                () -> selectByStore(storeId, criteria),
                () -> countByStore(storeId, criteria));
    }

    private TypedQuery<CustomerEntity> selectByStore(UUID storeId, CustomerCriteria criteria) {
        var selectString = "select c from CustomerEntity as c  " +
                "left join fetch c.delivery.country " +
                "left join fetch c.delivery.zone " +
                "left join fetch c.billing.country " +
                "left join fetch c.billing.zone " +
                "left join fetch c.attributes ca " +
                "left join fetch ca.option cao " +
                "left join fetch ca.optionValue cav " +
                "left join fetch cao.descriptions caod " +
                "left join fetch cav.descriptions " +
                "left join fetch c.groups";

        selectString += getFilterString(criteria);

        var query = this.em.createQuery(selectString, CustomerEntity.class);

        return applyFilterParameters(query, storeId, criteria);
    }

    private TypedQuery<Integer> countByStore(UUID storeId, CustomerCriteria criteria) {
        var selectString = "select count(c) from CustomerEntity as c";

        selectString += getFilterString(criteria);

        var query = em.createQuery(selectString, Integer.class);

        return applyFilterParameters(query, storeId, criteria);
    }

    private String getFilterString(CustomerCriteria criteria) {
        var filterString = new StringBuilder(" where c.merchantStore.id=:mId");

        if (!StringUtils.isBlank(criteria.getName())) {
            String nameQuery = " and c.billing.firstName like:nm or c.billing.lastName like:nm";
            filterString.append(nameQuery);
        }

        if (!StringUtils.isBlank(criteria.getFirstName())) {
            String nameQuery = " and c..billing.firstName like:fn";
            filterString.append(nameQuery);
        }

        if (!StringUtils.isBlank(criteria.getLastName())) {
            String nameQuery = " and c.billing.lastName like:ln";
            filterString.append(nameQuery);
        }

        if (!StringUtils.isBlank(criteria.getEmail())) {
            String mailQuery = " and c.emailAddress like:email";
            filterString.append(mailQuery);
        }

        if (!StringUtils.isBlank(criteria.getCountry())) {
            String countryQuery = " and c.billing.country.isoCode like:country";
            filterString.append(countryQuery);
        }

        return filterString.toString();
    }

    private <T> TypedQuery<T> applyFilterParameters(TypedQuery<T> query, UUID storeId, CustomerCriteria criteria) {
        query.setParameter("mId", storeId);

        if (!StringUtils.isBlank(criteria.getName())) {
            String nameParam = new StringBuilder().append("%").append(criteria.getName()).append("%").toString();
            query.setParameter("nm", nameParam);
        }

        if (!StringUtils.isBlank(criteria.getFirstName())) {
            String nameParam = new StringBuilder().append("%").append(criteria.getFirstName()).append("%").toString();
            query.setParameter("fn", nameParam);
        }

        if (!StringUtils.isBlank(criteria.getLastName())) {
            String nameParam = new StringBuilder().append("%").append(criteria.getLastName()).append("%").toString();
            query.setParameter("ln", nameParam);
        }

        if (!StringUtils.isBlank(criteria.getEmail())) {
            String email = new StringBuilder().append("%").append(criteria.getEmail()).append("%").toString();
            query.setParameter("email", email);
        }

        if (!StringUtils.isBlank(criteria.getCountry())) {
            String country = new StringBuilder().append("%").append(criteria.getCountry()).append("%").toString();
            query.setParameter("country", country);
        }

        return query;
    }
}
