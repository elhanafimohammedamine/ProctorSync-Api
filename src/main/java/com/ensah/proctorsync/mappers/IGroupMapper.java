package com.ensah.proctorsync.mappers;

import com.ensah.proctorsync.DTOs.group.GroupResponse;
import com.ensah.proctorsync.entities.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface IGroupMapper {

    Group groupResponseToGroup(GroupResponse groupResponse);
    Collection<Group> groupResponsesToGroups(Collection<GroupResponse> groupResponses);

    @Mapping(target = "professors", source = "professors")
    GroupResponse groupToGroupResponse(Group group);
    Collection<GroupResponse> groupsToGroupResponses(Collection<Group> groups);


}
