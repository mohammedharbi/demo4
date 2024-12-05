package com.example.blogsystem.Controller;

import com.example.blogsystem.ApiResponse.ApiResponse;
import com.example.blogsystem.Model.Post;
import com.example.blogsystem.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/get")
    public ResponseEntity findAllPosts() {
        return ResponseEntity.ok(postService.findAllPosts());
    }
    @PostMapping("/add")
    public ResponseEntity addPost(@RequestBody @Valid Post post, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        }
        postService.addPost(post);
        return ResponseEntity.status(200).body(new ApiResponse("Post added successfully"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updatePost(@PathVariable Integer id, @RequestBody @Valid Post post, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        }
        postService.updatePost(id, post);
        return ResponseEntity.ok(new ApiResponse("Post updated successfully"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
        return ResponseEntity.ok(new ApiResponse("Post deleted successfully"));
    }
    @GetMapping("/get/all-posts-user/{id}")
    public ResponseEntity findAllPostsUser(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(postService.getAllByUserID(id));
    }

    @GetMapping("/get-post-title/{title}")
    public ResponseEntity findAllPostTitle(@PathVariable String title) {
        return ResponseEntity.status(200).body(postService.findPostByTitle(title));
    }

    @GetMapping("/get-posts-publishdate/{date}")
    public ResponseEntity findAllPostsPublishDate(@PathVariable LocalDateTime date) {
        return ResponseEntity.status(200).body(postService.getPostsByPublishDate(date));
    }
    @GetMapping("/getfind-all-posts-bycategory/{id}")
    public ResponseEntity findAllPostsCategory(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(postService.getPostsByCategoryID(id));
    }

    @GetMapping("/find-posts-publishdatebefore/{date}")
    public ResponseEntity findAllPostsPublishDateBefore(@PathVariable LocalDateTime date) {
        return ResponseEntity.status(200).body(postService.getPostsByPublishDate(date));
    }

    @GetMapping("/find-posts-contentLength-greateryhanequal/{length}")
    public ResponseEntity findAllPostsContentLengthGreateryhanequal(@PathVariable Integer length) {
        return ResponseEntity.ok().body(postService.findPostsByContentLengthGreaterThanEqual(length));
    }
}