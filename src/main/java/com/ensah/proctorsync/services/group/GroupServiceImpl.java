package com.ensah.proctorsync.services.group;

import com.ensah.proctorsync.DTOs.group.GroupResponse;
import com.ensah.proctorsync.DTOs.group.GroupUpdateRequest;
import com.ensah.proctorsync.DTOs.group.NewGroupRequest;
import com.ensah.proctorsync.entities.Group;
import com.ensah.proctorsync.entities.Professor;
import com.ensah.proctorsync.exception.AlreadyExistException;
import com.ensah.proctorsync.exception.ApiRequestException;
import com.ensah.proctorsync.exception.NotFoundException;
import com.ensah.proctorsync.helpers.OperationCheck;
import com.ensah.proctorsync.mappers.IGroupMapper;
import com.ensah.proctorsync.repositories.group.IGroupRepository;
import com.ensah.proctorsync.services.professor.IProfessorService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupServiceImpl implements IGroupService {

    private final IGroupRepository groupRepository;
    private final IProfessorService professorService;
    private final IGroupMapper groupMapper;
    private final static Logger LOGGER = LoggerFactory.getLogger(GroupServiceImpl.class);

    @Override
    public Collection<GroupResponse> getAllGroups() {
        return groupMapper.groupsToGroupResponses(
                groupRepository.findAllByDeletedAtIsNullOrderByCreatedAtDesc()
        );
    }

    @Override
    public GroupResponse getGroupById(UUID groupId) {
        Group group = groupRepository
                .findById(groupId)
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Group with id " + groupId + " does not exist");
                            LOGGER.error("Error while getting group by id, exception thrown because of group does not exist", notFoundException);
                            return notFoundException;
                        }
                );


        return groupMapper.groupToGroupResponse(group);
    }

    @Override
    public String save(NewGroupRequest newGroupRequest) {

        // check whether the group name already exist
        if (groupRepository.existsByGroupName(newGroupRequest.getGroupName())) {
            AlreadyExistException alreadyExistException = new AlreadyExistException("Group with name " + newGroupRequest.getGroupName() + " already exists");
            LOGGER.error("Error while creating group, group with name {} alreay exist ", newGroupRequest.getGroupName(), alreadyExistException);
            throw alreadyExistException;
        }

        // build group object
        Group newGroup = Group
                .builder()
                .groupName(newGroupRequest.getGroupName())
                .description(newGroupRequest.getDescription())
                .createdAt(LocalDateTime.now())
                .build();

        Group savedGroup = groupRepository.save(newGroup);

        return OperationCheck.check(savedGroup, "Group created successfully", "Failed to create new group");
    }

    @Override
    public String update(UUID groupId, GroupUpdateRequest updateGroupRequest) {

        // check ids matching
        if (groupId != updateGroupRequest.getId()) {
            ApiRequestException apiRequestException = new ApiRequestException("Id of group does not match the id in the request body");
            LOGGER.error("Error while updating group, exception thrown because of group id {} does not match the one in the request body {} ", groupId, updateGroupRequest.getId(), apiRequestException);
            throw  apiRequestException;
        }

        // check whether th group exists
        Group group = groupRepository
                .findById(groupId)
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Group with id " + updateGroupRequest.getId() + " does not exist");
                            LOGGER.error("Error while updating group, exception thrown because of group does not exist", notFoundException);
                            return notFoundException;
                        }
                );

        group.setGroupName(updateGroupRequest.getGroupName());
        group.setDescription(updateGroupRequest.getDescription());
        group.setUpdatedAt(LocalDateTime.now());

        Group updatedGroup = groupRepository.save(group);

        return OperationCheck.check(updatedGroup, "Group updated successfully", "Failed to update group");
    }

    @Override
    public String addProfessorsToGroup(UUID groupId, Collection<UUID> professorIds) {

        Group group = groupRepository
                .findById(groupId)
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Group with id " + groupId + " does not exist");
                            LOGGER.error("Error while deleting group, exception thrown because of group does not exist", notFoundException);
                            return notFoundException;
                        }
                );

        Collection<Professor> professors = professorService.getProfessorsById(professorIds);
        professors.forEach(professor -> professor.setGroup(group));
        group.getProfessors().addAll(professors);

        Group savedGroup = groupRepository.save(group);

        return OperationCheck.check(savedGroup, "Members added successfully", "Failed to add members to group");
    }


    @Override
    public String delete(UUID groupId) {

        Group group = groupRepository
                .findById(groupId)
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Group with id " + groupId + " does not exist");
                            LOGGER.error("Error while deleting group, exception thrown because of group does not exist", notFoundException);
                            return notFoundException;
                        }
                );

        group.setDeletedAt(LocalDateTime.now());
        group.getProfessors().forEach(prof -> prof.setGroup(null));
        group.setProfessors(Collections.emptyList());
        Group deletedGroup = groupRepository.save(group);

        return OperationCheck.check(deletedGroup, "Group deleted successfully", "Failed to delete group");
    }

}
