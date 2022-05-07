package com.amazingenergy.saitamadomain.user.repository;

import com.amazingenergy.core.repository.EntityRepository;
import com.amazingenergy.saitamadomain.user.domain.Group;
import com.amazingenergy.saitamadomain.user.domain.GroupType;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface GroupRepository extends EntityRepository<UUID, Group> {
    List<Group> listGroup(GroupType groupType);

    List<Group> listGroupByIds(Set<Integer> ids);

    List<Group> listGroupByNames(List<String> names);

    Group findByName(String groupName);
}
