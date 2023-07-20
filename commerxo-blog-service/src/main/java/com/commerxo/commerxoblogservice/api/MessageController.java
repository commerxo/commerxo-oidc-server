//package com.commerxo.commerxoblogservice.api;
//
//import com.commerxo.commerxoblogservice.api.post.PostService;
//import com.commerxo.commerxoblogservice.domain.Message;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.UUID;
//
//@RestController
//public class MessageController {
//
//    private final PostService postRepository;
//
//    public MessageController(PostService postRepository) {
//        this.postRepository = postRepository;
//    }
//
//    @GetMapping("/")
//    ResponseEntity<Object> message() {
//        Message  message = new Message();
//        message.setId(UUID.randomUUID().toString());
//        message.setMessage("Hey " + message.getId());
//        return ResponseEntity.ok(postRepository.findById("1"));
//    }
//}
