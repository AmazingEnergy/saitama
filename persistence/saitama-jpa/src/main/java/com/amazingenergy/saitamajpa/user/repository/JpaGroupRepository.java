package com.amazingenergy.saitamajpa.user.repository;

import com.amazingenergy.saitamadomain.user.domain.GroupType;
import com.amazingenergy.saitamajpa.user.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface JpaGroupRepository extends JpaRepository<GroupEntity, UUID> {
    Optional<GroupEntity> findById(UUID id);

    @Query("select g from GroupEntity as g " +
            "left join fetch g.permissions perms " +
            "where g.groupName =?1")
    Optional<GroupEntity> findByGroupName(String name);

    @Query("select distinct g from GroupEntity as g " +
            "left join fetch g.permissions perms " +
            "order by g.id")
    List<GroupEntity> findAll();

    @Query("select distinct g from GroupEntity as g " +
            "left join fetch g.permissions perms " +
            "where perms.id in (?1) ")
    List<GroupEntity> findByPermissions(Set<UUID> permissionIds);

    @Query("select distinct g from GroupEntity as g " +
            "left join fetch g.permissions perms " +
            "where g.id in (?1) ")
    List<GroupEntity> findByIds(Set<UUID> groupIds);

    @Query("select distinct g from GroupEntity as g " +
            "left join fetch g.permissions perms " +
            "where g.groupName in (?1) ")
    List<GroupEntity> findByNames(List<String> groupNames);

    @Query("select distinct g from GroupEntity as g " +
            "left join fetch g.permissions perms " +
            "where g.groupType = ?1")
    List<GroupEntity> findByType(GroupType type);
}
