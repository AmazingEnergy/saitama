package com.amazingenergy.saitamajpa.catalog.product.repository;

import com.amazingenergy.saitamajpa.catalog.product.entity.review.ProductReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaProductReviewRepository extends JpaRepository<ProductReviewEntity, UUID> {
    @Query("select p from ProductReviewEntity p " +
            "join fetch p.customer pc " +
            "join fetch p.product pp " +
            "join fetch pp.merchantStore ppm " +
            "left join fetch p.descriptions pd " +
            "where p.id=?1")
    Optional<ProductReviewEntity> findOne(UUID id);

    @Query("select p from ProductReviewEntity p " +
            "join fetch p.customer pc " +
            "join fetch p.product pp " +
            "join fetch pp.merchantStore ppm " +
            "left join fetch p.descriptions pd " +
            "where pc.id=?1")
    List<ProductReviewEntity> findByCustomer(UUID customerId);

    @Query("select p from ProductReviewEntity p " +
            "left join fetch p.descriptions pd " +
            "join fetch p.customer pc " +
            "join fetch pc.merchantStore pcm " +
            "left join fetch pc.defaultLanguage pcl " +
            "left join fetch pc.attributes pca " +
            "left join fetch pca.option pcao " +
            "left join fetch pca.optionValue pcav " +
            "left join fetch pcao.descriptions pcaod " +
            "left join fetch pcav.descriptions pcavd " +
            "join fetch p.product pp " +
            "join fetch pp.merchantStore ppm " +
            "join fetch p.product pp " +
            "join fetch pp.merchantStore ppm " +
            "left join fetch p.descriptions pd " +
            "where pp.id = ?1")
    List<ProductReviewEntity> findByProduct(UUID productId);

    @Query("select p from ProductReviewEntity p " +
            "join fetch p.product pp " +
            "join fetch pp.merchantStore ppm " +
            "where pp.id = ?1")
    List<ProductReviewEntity> findByProductNoCustomers(UUID productId);

    @Query("select p from ProductReviewEntity p " +
            "left join fetch p.descriptions pd " +
            "join fetch p.customer pc " +
            "join fetch pc.merchantStore pcm " +
            "left join fetch pc.defaultLanguage pcl " +
            "left join fetch pc.attributes pca " +
            "left join fetch pca.option pcao " +
            "left join fetch pca.optionValue pcav " +
            "left join fetch pcao.descriptions pcaod " +
            "left join fetch pcav.descriptions pcavd " +
            "join fetch p.product pp " +
            "join fetch pp.merchantStore ppm " +
            "join fetch p.product pp " +
            "join fetch pp.merchantStore ppm " +
            "left join fetch p.descriptions pd " +
            "where pp.id = ?1 and pd.language.id =?2")
    List<ProductReviewEntity> findByProduct(UUID productId, UUID languageId);

    @Query("select p from ProductReviewEntity p " +
            "left join fetch p.descriptions pd " +
            "join fetch p.customer pc " +
            "join fetch pc.merchantStore pcm " +
            "left join fetch pc.defaultLanguage pcl " +
            "left join fetch pc.attributes pca " +
            "left join fetch pca.option pcao " +
            "left join fetch pca.optionValue pcav " +
            "left join fetch pcao.descriptions pcaod " +
            "left join fetch pcav.descriptions pcavd " +
            "join fetch p.product pp " +
            "join fetch pp.merchantStore ppm " +
            "join fetch p.product pp " +
            "join fetch pp.merchantStore ppm " +
            "left join fetch p.descriptions pd " +
            "where pp.id = ?1 and pc.id = ?2")
    Optional<ProductReviewEntity> findByProductAndCustomer(UUID productId, UUID customerId);
}
