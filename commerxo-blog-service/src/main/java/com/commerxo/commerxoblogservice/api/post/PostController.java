package com.commerxo.commerxoblogservice.api.post;

import com.commerxo.commerxoblogservice.common.ConstantExtension;
import com.commerxo.commerxoblogservice.domain.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PostController.ENDPOINT)
@Repository
public class PostController {

    public static final String ENDPOINT = ConstantExtension.API_VERSION + "/post";

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(
            path = "/create",
            method = RequestMethod.GET
    )
    public ResponseEntity<Post> create(){
        Post post = Post
                .withId("2")
                .title("11")
                .content("124")
                .build();
        postService.saveOrUpdate(post);
        return ResponseEntity.ok()
                .body(post);
    }


}