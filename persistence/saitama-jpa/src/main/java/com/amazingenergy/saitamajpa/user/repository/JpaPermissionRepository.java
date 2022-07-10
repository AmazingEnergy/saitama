package com.amazingenergy.saitamajpa.user.repository;

import com.amazingenergy.saitamajpa.user.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface JpaPermissionRepository extends JpaRepository<PermissionEntity, UUID>, CustomPermissionRepository{
    @Query("select p from PermissionEntity as p where p.id = ?1")
    Optional<PermissionEntity> findOne(UUID id);

    @Query("select p from PermissionEntity as p order by p.id")
    List<PermissionEntity> findAll();

    @Query("select distinct p from PermissionEntity as p " +
            "join fetch p.groups groups " +
            "where groups.id in (?1)")
    List<PermissionEntity> findByGroups(Set<UUID> groupIds);
}
