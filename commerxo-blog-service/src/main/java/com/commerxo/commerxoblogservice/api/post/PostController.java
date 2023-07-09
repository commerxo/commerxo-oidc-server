package com.commerxo.commerxoblogservice.api.post;

import com.commerxo.commerxoblogservice.common.ConstantExtension;
import com.commerxo.commerxoblogservice.domain.Post;
import com.commerxo.commerxoblogservice.http.APIResponse;
import com.commerxo.commerxoblogservice.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PostController.ENDPOINT)
public class PostController {

    public static final String ENDPOINT = ConstantExtension.API_VERSION + "/post";

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(
            path = "/create",
            method = RequestMethod.POST
    )
    public ResponseEntity<Post> create(@RequestBody PostCreateRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postService.create(request));
    }

    @RequestMapping(
            path = "{title}/delete",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<Post> delete(@PathVariable(name = "title") String title){
        return ResponseEntity
                .ok(this.postService.remove(title));
    }

}