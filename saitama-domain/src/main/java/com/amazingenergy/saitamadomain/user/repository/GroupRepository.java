package com.amazingenergy.saitamadomain.user.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.user.domain.Group;
import com.amazingenergy.saitamadomain.user.domain.GroupType;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface GroupRepository extends EntityRepository<UUID, Group> {
    List<Group> listGroup(GroupType groupType);

    List<Group> listGroupByIds(Set<UUID> ids);

    List<Group> listGroupByNames(List<String> names);

    Optional<Group> findByName(String groupName);
}
