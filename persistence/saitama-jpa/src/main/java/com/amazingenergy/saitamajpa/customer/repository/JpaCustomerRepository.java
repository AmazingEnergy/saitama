package com.amazingenergy.saitamajpa.customer.repository;

import com.amazingenergy.saitamajpa.customer.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaCustomerRepository extends JpaRepository<CustomerEntity, UUID>, CustomCustomerRepository {
    @Query("select c from CustomerEntity c " +
            "join fetch c.merchantStore cm " +
            "left join fetch c.defaultLanguage cl " +
            "left join fetch c.attributes ca " +
            "left join fetch ca.option cao " +
            "left join fetch ca.optionValue cav " +
            "left join fetch cao.descriptions caod " +
            "left join fetch cav.descriptions " +
            "left join fetch c.groups " +
            "where c.id = ?1")
    Optional<CustomerEntity> findOne(UUID id);

    @Query("select distinct c from CustomerEntity c " +
            "join fetch c.merchantStore cm " +
            "left join fetch c.defaultLanguage cl " +
            "left join fetch c.attributes ca " +
            "left join fetch ca.option cao " +
            "left join fetch cao.descriptions caod " +
            "left join fetch ca.optionValue cav " +
            "left join fetch cav.descriptions " +
            "left join fetch c.groups " +
            "where c.billing.firstName = ?1")
    List<CustomerEntity> findByName(String name);

    @Query("select c from CustomerEntity c " +
            "join fetch c.merchantStore cm " +
            "left join fetch c.defaultLanguage cl " +
            "left join fetch c.attributes ca " +
            "left join fetch ca.option cao " +
            "left join fetch cao.descriptions caod " +
            "left join fetch ca.optionValue cav " +
            "left join fetch cav.descriptions " +
            "left join fetch c.groups " +
            "where c.name = ?1")
    Optional<CustomerEntity> findByNick(String nick);

    @Query("select c from CustomerEntity c "
            + "join fetch c.merchantStore cm "
            + "left join fetch c.defaultLanguage cl "
            + "left join fetch c.attributes ca "
            + "left join fetch ca.option cao "
            + "left join fetch cao.descriptions caod "
            + "left join fetch ca.optionValue cav "
            + "left join fetch cav.descriptions  "
            + "left join fetch c.groups  "
            + "left join fetch c.delivery cd "
            + "left join fetch c.billing cb "
            + "left join fetch cd.country "
            + "left join fetch cd.zone "
            + "left join fetch cb.country "
            + "left join fetch cb.zone "
            + "where c.name = ?1 and cm.id = ?2")
    Optional<CustomerEntity> findByNick(String nick, UUID storeId);

    @Query("select c from CustomerEntity c "
            + "join fetch c.merchantStore cm "
            + "left join fetch c.defaultLanguage cl "
            + "left join fetch c.attributes ca "
            + "left join fetch ca.option cao "
            + "left join fetch cao.descriptions caod "
            + "left join fetch ca.optionValue cav "
            + "left join fetch cav.descriptions  "
            + "left join fetch c.groups  "
            + "left join fetch c.delivery cd "
            + "left join fetch c.billing cb "
            + "left join fetch cd.country "
            + "left join fetch cd.zone "
            + "left join fetch cb.country "
            + "left join fetch cb.zone "
            + "where c.name = ?1 and cm.code = ?2")
    Optional<CustomerEntity> findByNick(String nick, String storeCode);

    @Query("select distinct c from CustomerEntity c " +
            "join fetch c.merchantStore cm " +
            "left join fetch c.defaultLanguage cl " +
            "left join fetch c.attributes ca " +
            "left join fetch ca.option cao " +
            "left join fetch ca.optionValue cav " +
            "left join fetch cao.descriptions caod " +
            "left join fetch cav.descriptions " +
            "left join fetch c.groups " +
            "where cm.id = ?1")
    List<CustomerEntity> findByStore(UUID storeId);
}
