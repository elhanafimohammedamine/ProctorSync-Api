package com.ensah.proctorsync.services.group;

import com.ensah.proctorsync.DTOs.group.GroupResponse;
import com.ensah.proctorsync.DTOs.group.GroupUpdateRequest;
import com.ensah.proctorsync.DTOs.group.NewGroupRequest;



import java.util.Collection;
import java.util.UUID;

public interface IGroupService {
    Collection<GroupResponse> getAllGroups();
    GroupResponse getGroupById(UUID groupId);
    String save(NewGroupRequest newGroupRequest);
    String update(UUID groupId, GroupUpdateRequest updateGroupRequest);
    String delete(UUID groupId);
}
