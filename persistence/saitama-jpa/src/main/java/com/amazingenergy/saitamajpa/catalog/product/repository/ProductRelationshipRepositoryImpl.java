package com.amazingenergy.saitamajpa.catalog.product.repository;

import com.amazingenergy.corejpa.repository.BaseRepositoryImpl;
import com.amazingenergy.saitamadomain.catalog.product.domain.Product;
import com.amazingenergy.saitamadomain.catalog.product.domain.relationship.ProductRelationship;
import com.amazingenergy.saitamadomain.catalog.product.domain.relationship.ProductRelationshipType;
import com.amazingenergy.saitamadomain.catalog.product.repository.ProductRelationshipRepository;
import com.amazingenergy.saitamadomain.merchant.domain.MerchantStore;
import com.amazingenergy.saitamadomain.reference.domain.Language;
import com.amazingenergy.saitamajpa.catalog.product.ProductMapper;
import com.amazingenergy.saitamajpa.catalog.product.entity.relationship.ProductRelationshipEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ProductRelationshipRepositoryImpl extends BaseRepositoryImpl<UUID, ProductRelationship, ProductRelationshipEntity> implements ProductRelationshipRepository {

    private final JpaProductRelationshipRepository jpaRepository;
    private final ProductMapper mapper;

    public ProductRelationshipRepositoryImpl(JpaProductRelationshipRepository jpaRepository, ProductMapper mapper) {
        super(jpaRepository);
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ProductRelationship> getByType(MerchantStore store, Product product, ProductRelationshipType type, Language language) {
        return jpaRepository.getByCode(store.getId(), type.name(), product.getId(), language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<ProductRelationship> getByType(MerchantStore store, Product product, String name) {
        return jpaRepository.getByCode(store.getId(), name, product.getId(), true)
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<ProductRelationship> getByType(MerchantStore store, Product product, ProductRelationshipType type) {
        return jpaRepository.getByCode(store.getId(), type.name())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<ProductRelationship> getByType(MerchantStore store, ProductRelationshipType type) {
        return jpaRepository.getByCode(store.getId(), type.name())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<ProductRelationship> listByProduct(Product product) {
        return jpaRepository.listByProducts(product.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<ProductRelationship> getByType(MerchantStore store, ProductRelationshipType type, Language language) {
        return jpaRepository.getByCode(store.getId(), type.name(), language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<ProductRelationship> getByGroup(MerchantStore store, String groupName, Language language) {
        return jpaRepository.getByCode(store.getId(), groupName, language.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<ProductRelationship> getGroups(MerchantStore store) {
        return jpaRepository.getByStore(store.getId())
                .stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public void deleteGroup(MerchantStore store, String groupName) {
        var relationships = jpaRepository.getByCode(store.getId(), groupName);
        for (var relationship : relationships) {
            this.delete(this.from(relationship));
        }
    }

    @Override
    public void deactivateGroup(MerchantStore store, String groupName) {
        var relationships = jpaRepository.getByCode(store.getId(), groupName);
        for (var relationship : relationships) {
            relationship.setActive(false);
            this.save(this.from(relationship));
        }
    }

    @Override
    public void activateGroup(MerchantStore store, String groupName) {
        var relationships = jpaRepository.getByCode(store.getId(), groupName);
        for (var relationship : relationships) {
            relationship.setActive(true);
            this.save(this.from(relationship));
        }
    }

    @Override
    public ProductRelationshipEntity to(ProductRelationship source) {
        return mapper.to(source);
    }

    @Override
    public ProductRelationship from(ProductRelationshipEntity destination) {
        return mapper.from(destination);
    }
}
