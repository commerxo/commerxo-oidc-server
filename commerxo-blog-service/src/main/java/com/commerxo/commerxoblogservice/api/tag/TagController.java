package com.commerxo.commerxoblogservice.api.tag;

import com.commerxo.commerxoblogservice.common.ConstantExtension;
import com.commerxo.commerxoblogservice.domain.Tag;
import com.commerxo.commerxoblogservice.http.APIResponse;
import com.commerxo.commerxoblogservice.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(TagController.ENDPOINT)
@SecurityRequirement(
        name = "security_auth"
)
@io.swagger.v3.oas.annotations.tags.Tag(name = "Tag")
public class TagController {

    public static final String ENDPOINT = ConstantExtension.API_VERSION + "/tag";

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @Operation(
            description = "Create a new tag!",
            summary = "API to create a new tag",
            method = "POST",
            responses = {
                    @ApiResponse(
                            description = "success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Conflict / Resource Exist!",
                            responseCode = "409"
                    )
            }
    )
    @RequestMapping(
            path = "/create",
            method = RequestMethod.POST
    )
    public ResponseEntity<APIResponse<Tag>> create(@RequestBody TagRequest request){
        Tag tag = TagRequest.mapToEntity(request);
        APIResponse<Tag> apiResponse = tagService.save(tag);
        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }

    @RequestMapping(
            path = "/{tagName}/delete",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<APIResponse<Tag>> delete(@PathVariable(name = "tagName") String tagName){
        APIResponse<Tag> apiResponse = tagService.remove(tagName);
        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }

    @RequestMapping(
            path = "/all",
            method = RequestMethod.GET
    )
    public ResponseEntity<APIResponse<List<Tag>>> get(){
        APIResponse<List<Tag>> apiResponse = tagService.getAllTags();
        return
                ResponseEntity
                        .status(apiResponse.getStatus())
                        .body(apiResponse);
    }

}