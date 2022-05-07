package com.amazingenergy.saitamajpa.catalog.category.repository;

import com.amazingenergy.saitamajpa.catalog.category.entity.CategoryEntity;
import com.amazingenergy.saitamajpa.merchant.entity.MerchantStoreEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class CustomCategoryRepositoryImpl implements CustomCategoryRepository {
    private final EntityManager em;

    public CustomCategoryRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Object[]> countProductsByCategories(MerchantStoreEntity store, List<UUID> categoryIds) {
        var selectString = "select category.id, count(product.id) from ProductEntity product " +
                "inner join product.categories category " +
                "where category.id in (:cid) " +
                "and product.available=true and product.dateAvailable<=:dt " +
                "group by category.id";

        var q = this.em.createQuery(selectString);

        q.setParameter("cid", categoryIds);
        q.setParameter("dt", new Date());

        @SuppressWarnings("unchecked")
        List<Object[]> counts = q.getResultList();

        return counts;
    }

    @Override
    public List<CategoryEntity> listByStoreAndParent(MerchantStoreEntity store, CategoryEntity category) {
        var selectString = "select c from CategoryEntity c join fetch c.merchantStore cm ";

        if (store == null) {
            if (category == null) {
                //query.from(qCategory)
                selectString += " where c.parent IsNull ";
                //.where(qCategory.parent.isNull())
                //.orderBy(qCategory.sortOrder.asc(),qCategory.id.desc());
            } else {
                //query.from(qCategory)
                selectString += " join fetch c.parent cp where cp.id =:cid ";
                //.where(qCategory.parent.eq(category))
                //.orderBy(qCategory.sortOrder.asc(),qCategory.id.desc());
            }
        } else {
            if (category == null) {
                //query.from(qCategory)
                selectString += " where c.parent IsNull and cm.id=:mid ";
                //.where(qCategory.parent.isNull()
                //	.and(qCategory.merchantStore.eq(store)))
                //.orderBy(qCategory.sortOrder.asc(),qCategory.id.desc());
            } else {
                //query.from(qCategory)
                selectString += " join fetch c.parent cp where cp.id =:cId and cm.id=:mid ";
                //.where(qCategory.parent.eq(category)
                //	.and(qCategory.merchantStore.eq(store)))
                //.orderBy(qCategory.sortOrder.asc(),qCategory.id.desc());
            }
        }

        selectString += " order by c.sortOrder asc";

        var q = this.em.createQuery(selectString, CategoryEntity.class);

        q.setParameter("cid", category.getId());
        if (store != null) {
            q.setParameter("mid", store.getId());
        }

        return q.getResultList();
    }
}
