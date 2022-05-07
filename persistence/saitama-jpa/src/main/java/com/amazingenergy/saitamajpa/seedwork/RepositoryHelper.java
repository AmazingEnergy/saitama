package com.amazingenergy.saitamajpa.seedwork;

import com.amazingenergy.core.Criteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.persistence.TypedQuery;
import java.util.function.Supplier;

public class RepositoryHelper {
    public static <T> Page<T> paginateQuery(Criteria criteria, Supplier<TypedQuery<T>> querySup, Supplier<TypedQuery<Integer>> countSup) {
        var query = querySup.get();
        //int firstResult = ((criteria.getStartPage()==0?criteria.getStartPage()+1:criteria.getStartPage()) - 1) * criteria.getPageSize();
        int firstResult = ((criteria.getStartPage() == 0 ? 0 : criteria.getStartPage())) * criteria.getPageSize();
        query.setFirstResult(firstResult);
        query.setMaxResults(criteria.getPageSize());
        var items = query.getResultList();

        var countQuery = countSup.get();
        var totalCount = countQuery.getSingleResult();

        return new PageImpl(items, PageRequest.of(criteria.getStartPage(), criteria.getPageSize()), totalCount);
    }
}
