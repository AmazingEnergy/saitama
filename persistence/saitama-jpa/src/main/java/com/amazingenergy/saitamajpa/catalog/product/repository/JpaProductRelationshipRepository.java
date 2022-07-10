package com.amazingenergy.saitamajpa.catalog.product.repository;

import com.amazingenergy.saitamajpa.catalog.product.entity.relationship.ProductRelationshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaProductRelationshipRepository extends JpaRepository<ProductRelationshipEntity, UUID> {
    @Query("select distinct pr from ProductRelationshipEntity as pr "
            + "where pr.code=?2 and pr.product is null")
    List<ProductRelationshipEntity> getByStore(UUID storeId);

    @Query("select distinct pr from ProductRelationshipEntity as pr "
            + "left join fetch pr.product p "
            + "join fetch pr.relatedProduct rp "
            + "left join fetch rp.descriptions rp_desc "
            + "where pr.code=?2 and pr.store.id=?1")
    List<ProductRelationshipEntity> getByCode(UUID storeId, String code);

    @Query("select distinct pr from ProductRelationshipEntity as pr "
            + "left join fetch pr.product p "
            // related product
            + "join fetch pr.relatedProduct rp "
            + "left join fetch rp.descriptions rp_desc "
            + "left join fetch rp.owner rp_owner "
            + "left join fetch rp.images rp_img "
            + "left join fetch rp.type rp_type "
            // related product's attributes
            + "left join fetch rp.attributes rp_attr "
            + "left join fetch rp_attr.option rp_opt "
            + "left join fetch rp_opt.descriptions rp_opt_desc "
            + "left join fetch rp_attr.optionValue rp_optval "
            + "left join fetch rp_optval.descriptions rp_optval_desc "
            // related product's categories
            + "left join fetch rp.categories rp_categ "
            + "left join fetch rp_categ.descriptions rrp_categ_desc "
            // related product's merchant store
            + "left join fetch rp.merchantStore rp_merch "
            + "left join fetch rp_merch.currency rp_merch_curr "
            // related product's manufacturer
            + "left join fetch rp.manufacturer rp_manu "
            + "left join fetch rp_manu.descriptions rp_manu_desc "
            // related product's availabilities
            + "left join fetch rp.availabilities rp_avail "
            + "left join fetch rp_avail.prices rp_avail_price "
            + "left join fetch rp_avail_price.descriptions rp_avail_price_desc "
            // filter
            + "where pr.code=?2 and pr.store.id=?1 and rp_desc.language.id=?3")
    List<ProductRelationshipEntity> getByCode(UUID storeId, String code, UUID languageId);

    @Query("select distinct pr from ProductRelationshipEntity as pr "
            + "left join fetch pr.product p "
            + "join fetch pr.relatedProduct rp "
            + "left join fetch rp.descriptions rp_desc "
            + "where pr.code=?2 and pr.store.id=?1 and p.id=?3 and rp_desc.language.id=?4")
    List<ProductRelationshipEntity> getByCode(UUID storeId, String code, UUID productId, UUID languageId);

    @Query("select distinct pr from ProductRelationshipEntity as pr "
            + "left join fetch pr.product p "
            // related product
            + "join fetch pr.relatedProduct rp "
            + "left join fetch rp.descriptions rp_desc "
            + "left join fetch rp.owner rp_owner "
            + "left join fetch rp.images rp_img "
            + "left join fetch rp.type rp_type "
            // related product's attributes
            + "left join fetch rp.attributes rp_attr "
            + "left join fetch rp_attr.option rp_opt "
            + "left join fetch rp_opt.descriptions rp_opt_desc "
            + "left join fetch rp_attr.optionValue rp_optval "
            + "left join fetch rp_optval.descriptions rp_optval_desc "
            // related product's categories
            + "left join fetch rp.categories rp_categ "
            + "left join fetch rp_categ.descriptions rrp_categ_desc "
            // related product's merchant store
            + "left join fetch rp.merchantStore rp_merch "
            + "left join fetch rp_merch.currency rp_merch_curr "
            // related product's manufacturer
            + "left join fetch rp.manufacturer rp_manu "
            + "left join fetch rp_manu.descriptions rp_manu_desc "
            // related product's availabilities
            + "left join fetch rp.availabilities rp_avail "
            + "left join fetch rp_avail.prices rp_avail_price "
            + "left join fetch rp_avail_price.descriptions rp_avail_price_desc "
            // filter
            + "where pr.code=?2 and pr.store.id=?1 and p.id=?3 and rp.available=?4")
    List<ProductRelationshipEntity> getByCode(UUID storeId, String code, UUID productId, boolean available);

    @Query("select distinct pr from ProductRelationshipEntity as pr "
            + "left join fetch pr.product p "
            + "left join fetch p.descriptions p_desc "
            + "left join fetch pr.relatedProduct rp "
            + "left join fetch rp.attributes rp_attr "
            + "left join fetch rp.categories rp_categ "
            + "left join fetch rp.descriptions rp_desc "
            + "where p.id=?1 or rp.id=?1")
    List<ProductRelationshipEntity> listByProducts(UUID productId);
}
