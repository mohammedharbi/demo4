package com.example.blogsystem.Repository;

import com.example.blogsystem.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepostiory extends JpaRepository<Comment, Integer> {

    Comment findCommentById(Integer id);

    List<Comment> findCommentByPostID(Integer id);



}