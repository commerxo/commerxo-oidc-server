package com.commerxo.commerxoopenidserver.service.impl;

import com.commerxo.commerxoopenidserver.api.group.UserGroupCreationRequest;
import com.commerxo.commerxoopenidserver.api.group.UserGroupUpdateRequest;
import com.commerxo.commerxoopenidserver.domain.UserGroup;
import com.commerxo.commerxoopenidserver.exception.ResourceAlreadyExistException;
import com.commerxo.commerxoopenidserver.exception.ResourceNotFoundException;
import com.commerxo.commerxoopenidserver.repository.UserGroupRepository;
import com.commerxo.commerxoopenidserver.service.GroupService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    private final UserGroupRepository userGroupRepository;

    public GroupServiceImpl(UserGroupRepository userGroupRepository) {
        this.userGroupRepository = userGroupRepository;
    }

    @Override
    public UserGroup register(UserGroupCreationRequest request) {
        Optional<UserGroup> existUserGroup = this.userGroupRepository.findByGroupName(request.getGroupName());
        if(existUserGroup.isPresent()){
            throw new ResourceAlreadyExistException("UserGroup already exist with group name [ " + request.getGroupName() +" ]");
        }
        UserGroup group = UserGroupCreationRequest.mapToEntity(request);
        return userGroupRepository.save(group);
    }

    @Override
    public void delete(String groupName) {
        UserGroup userGroup = userGroupRepository.findByGroupName(groupName).orElseThrow(() ->
                new ResourceNotFoundException("UserGroup does not exist with group name [ " + groupName +" ]")
        );
        userGroupRepository.delete(userGroup);
    }

    public List<UserGroup> get(){
        return this.userGroupRepository.findAll();
    }
    @Override
    public UserGroup update(UserGroupUpdateRequest request, String groupName) {
        UserGroup savedGroup = userGroupRepository.findByGroupName(groupName).orElseThrow(() ->
                        new ResourceNotFoundException("UserGroup does not exist with group name [ " + groupName +" ]")
                );
        if(request.isEnabled()){
            savedGroup.setEnabled(request.isEnabled());
        }
        if(StringUtils.hasText(request.getDescription())){
            savedGroup.setDescription(request.getDescription());
        }
        if(!request.getAuthorities().isEmpty()) {
            request.getAuthorities().forEach(authority->
                            savedGroup.getGroupAuthorities().add(new SimpleGrantedAuthority(authority))
                    );
        }
        return userGroupRepository.save(savedGroup);
    }

    @Override
    public UserGroup active(String groupName) {
        return null;
    }

    @Override
    public UserGroup inActive(String groupName) {
        return null;
    }
}