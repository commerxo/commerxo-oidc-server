package com.commerxo.commerxoopenidserver.api.group;

import com.commerxo.commerxoopenidserver.common.APIConstant;
import com.commerxo.commerxoopenidserver.common.APIResponse;
import com.commerxo.commerxoopenidserver.domain.UserGroup;
import com.commerxo.commerxoopenidserver.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UserGroupController.ENDPOINT)
public class UserGroupController {

    public static final String ENDPOINT = APIConstant.VERSION + "/user/group";

    private final GroupService groupService;

    public UserGroupController(GroupService groupService) {
        this.groupService = groupService;
    }
    @RequestMapping(
            path = "/create",
            method = RequestMethod.POST
    )
    public ResponseEntity<APIResponse<UserGroup>> create(@RequestBody UserGroupCreationRequest request){
        UserGroup savedUserGroup = groupService.register(request);
        APIResponse<UserGroup> apiResponse = new APIResponse<>(HttpStatus.CREATED, savedUserGroup, APIConstant.SUCCESS_CREATED);
        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }

    @RequestMapping(
            path = "/",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> get(){
        return ResponseEntity.ok(groupService.get());
    }

    @RequestMapping(
            path = "/{groupName}",
            method = RequestMethod.PUT
    )
    public ResponseEntity<APIResponse<UserGroup>> updateGroup(@PathVariable(name = "groupName") String groupName,
                                                              @RequestBody UserGroupUpdateRequest updateRequest){
        UserGroup updatedUserGroup = groupService.update(updateRequest, groupName);
        APIResponse<UserGroup> apiResponse = new APIResponse<>(HttpStatus.OK, updatedUserGroup, APIConstant.SUCCESS_CREATED);
        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }

    @RequestMapping(
            path = "/{groupName}/delete",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<APIResponse<UserGroup>> delete(@PathVariable(name = "groupName") String groupName){
        groupService.delete(groupName);
        APIResponse<UserGroup> apiResponse = new APIResponse<>(HttpStatus.OK,"Success Deleted!");
        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }
}