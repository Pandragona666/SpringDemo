package pl.sda.spring.demo.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sda.spring.demo.model.Post;
import pl.sda.spring.demo.model.enums.CategoryEnum;
import pl.sda.spring.demo.service.PostService;

@RestController
public class PostController {

    PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/addPost/{id}")
    public String addPost(@PathVariable Long id, String title, String content, CategoryEnum category){
        return postService.savePost(title, content, category, id);
    }

    @PutMapping("/changeTitle/{userId}/{postId}")
    public String changeTitle(@PathVariable Long userId, @PathVariable Long postId, String newTitle){
        return postService.changeTitle(userId, postId, newTitle);
    }

    @DeleteMapping("/deletePost/{postId}/{userId}")
    public void deletePost(@PathVariable Long postId, @PathVariable Long userId){
        postService.removePost(userId, postId);
    }

}
