package com.example.blogsystem.Service;

import com.example.blogsystem.ApiResponse.ApiException;
import com.example.blogsystem.Model.Post;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Repository.CategoryRepository;
import com.example.blogsystem.Repository.PostRepository;
import com.example.blogsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }
    public void addPost(Post post) {

        if (categoryRepository.findCategoriesById(post.getCategoryID())==null) {
            throw new ApiException("category id not found");
        }

        if (userRepository.findUserById(post.getUserID())==null) {
            throw new ApiException("user id not found");
        }

        postRepository.save(post);
    }

    public void updatePost(Integer id,Post post) {
        Post oldPost = postRepository.findPostById(id);

        if (categoryRepository.findCategoriesById(post.getCategoryID() ) == null) {
            throw new ApiException("category id not found");
        }
        if (userRepository.findUserById(post.getUserID())==null) {
            throw new ApiException("user id not found");
        }

        if (oldPost != null) {
            oldPost.setTitle(post.getTitle());
            oldPost.setContent(post.getContent());
             postRepository.save(oldPost);
        }else throw new ApiException("post not found");
    }

    public void deletePost(Integer id) {
        Post oldPost = postRepository.findPostById(id);

        if (oldPost == null) {
            throw new ApiException("post not found");
        }else postRepository.delete(oldPost);
    }

    public List<Post> getAllByUserID(Integer id) {
        List<Post> posts = postRepository.getPostsByUserID(id);
        if (posts == null) {throw new ApiException("post by user not found");
        }else return posts;
    }

    public Post findPostByTitle(String title) {
        return postRepository.findPostByTitle(title);
    }

    public List<Post> getPostsByPublishDate(LocalDateTime date) {
        return postRepository.getPostsByPublishDate(date);
    }

    public List<Post> getPostsByCategoryID(Integer id) {
        return postRepository.findAllByCategoryID(id);
    }

    public List<Post> findPostsByContentLengthGreaterThanEqual(Integer contentLength) {
        return postRepository.findPostsByContentLengthGreaterThanEqual(contentLength);
    }
}
