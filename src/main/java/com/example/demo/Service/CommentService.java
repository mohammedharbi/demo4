package com.example.blogsystem.Service;

import com.example.blogsystem.ApiResponse.ApiException;
import com.example.blogsystem.Model.Comment;
import com.example.blogsystem.Repository.CommentRepostiory;
import com.example.blogsystem.Repository.PostRepository;
import com.example.blogsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepostiory commentRepostiory;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public List<Comment> getAllComments() {
        return commentRepostiory.findAll();
    }
    public void addComment(Comment comment) {
        if (userRepository.findUserById(comment.getUserID())==null){throw new ApiException("user not found");}
        if (postRepository.findPostById(comment.getPostID())==null){throw new ApiException("post not found");}
        commentRepostiory.save(comment);
    }
    public void updateComment(Integer id,Comment comment) {
        Comment oldComment = commentRepostiory.findCommentById(id);

        if (userRepository.findUserById(comment.getUserID())==null){throw new ApiException("user not found");}
        if (postRepository.findPostById(comment.getPostID())==null){throw new ApiException("post not found");}
        if (oldComment!=null){
            oldComment.setContent(comment.getContent());
            oldComment.setPostID(comment.getPostID());
            oldComment.setUserID(comment.getUserID());
            commentRepostiory.save(oldComment);
        }else throw new ApiException("comment not found");
    }

    public void deleteComment(Integer id) {
        Comment comment = commentRepostiory.findCommentById(id);
        if (comment==null){throw new ApiException("comment not found");
        }else commentRepostiory.delete(comment);
    }

    public List<Comment> allCommentByPostID(Integer postID) {
        return commentRepostiory.findCommentByPostID(postID);
    }
}
