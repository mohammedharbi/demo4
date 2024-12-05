package com.example.blogsystem.Controller;

import com.example.blogsystem.ApiResponse.ApiResponse;
import com.example.blogsystem.Model.Comment;
import com.example.blogsystem.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/get")
    public ResponseEntity getAllComment() {
        return ResponseEntity.status(200).body(commentService.getAllComments());
    }
    @PostMapping("/add")
    public ResponseEntity addComment(@RequestBody @Valid Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.addComment(comment);
        return ResponseEntity.status(201).body(new ApiResponse("added comment successfully"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity  updateComment(@PathVariable Integer id, @RequestBody @Valid Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.updateComment(id, comment);
        return ResponseEntity.status(201).body(new ApiResponse("updated comment successfully"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity  deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
        return ResponseEntity.status(201).body(new ApiResponse("deleted comment successfully"));
    }

    @GetMapping("/all-Comment-post/{postid}")
    public ResponseEntity getAllCommentPostID(@PathVariable Integer postid) {
        return ResponseEntity.status(200).body(commentService.allCommentByPostID(postid));
    }
}
