package com.example.blogsystem.Repository;

import com.example.blogsystem.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Post findPostById(Integer id);

    @Query("select u from Post u where u.userID=?1")
    List<Post> getPostsByUserID(Integer id);

    Post findPostByTitle(String title);

    List<Post> getPostsByPublishDate(LocalDateTime publishDate);

    List<Post> findAllByCategoryID(Integer id);

    @Query("SELECT p FROM Post p WHERE LENGTH(p.content) >= :num")
    List<Post> findPostsByContentLengthGreaterThanEqual(Integer num);
    //List<Post> findPostsByPublishDateBefore(LocalDateTime publishDate);
}