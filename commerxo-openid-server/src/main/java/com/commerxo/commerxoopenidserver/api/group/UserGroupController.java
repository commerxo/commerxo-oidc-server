package com.commerxo.commerxoopenidserver.api.group;

import com.commerxo.commerxoopenidserver.common.APIConstant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserGroupController.ENDPOINT)
public class UserGroupController {

    public static final String ENDPOINT = APIConstant.VERSION + "/user/group";

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserGroupCreationRequest request){
        System.out.println(request);
        return null;
    }

}
