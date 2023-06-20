package com.commerxo.commerxoblogservice.api.tag;

import com.commerxo.commerxoblogservice.domain.Tag;
import com.commerxo.commerxoblogservice.http.APIResponse;
import com.commerxo.commerxoblogservice.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TagController.ENDPOINT)
public class TagController {

    private static final String API_VERSION = "/api/v1";
    public static final String ENDPOINT = API_VERSION + "/tag";

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

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

}
