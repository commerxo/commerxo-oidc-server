//package com.commerxo.commerxoopenidserver.service.impl;
//
//import com.commerxo.commerxoopenidserver.api.group.UserGroupCreateRequest;
//import com.commerxo.commerxoopenidserver.models.UserGroup;
//import com.commerxo.commerxoopenidserver.repository.UserGroupRepository;
//import com.commerxo.commerxoopenidserver.service.GroupService;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class GroupServiceImpl implements GroupService {
//
//    private final UserGroupRepository userGroupRepository;
//
//    public GroupServiceImpl(UserGroupRepository userGroupRepository) {
//        this.userGroupRepository = userGroupRepository;
//    }
//
//    @Override
//    public String register(UserGroupCreateRequest request) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserGroup userGroup = UserGroupCreateRequest.mapToUserGroup(request);
//        userGroup.setCreatedBy(authentication.getName());
//        userGroup.setCreatedBy(authentication.getName());
//        userGroupRepository.save(userGroup);
//        return "Success";
//    }
//
//    @Override
//    public void delete(String groupName) {
//
//    }
//
//    @Override
//    public List<UserGroup> get() {
//        return null;
//    }
//
//    @Override
//    public UserGroup active(String groupName) {
//        return null;
//    }
//
//    @Override
//    public UserGroup inActive(String groupName) {
//        return null;
//    }
//
//}