package com.example.blogsystem.Repository;

import com.example.blogsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer id);

    User findUserByEmailContaining(String email);

    @Query("SELECT U FROM User U WHERE LENGTH(U.username) BETWEEN :min AND :max")
    List<User> findUserByUsernameBetween(Integer min , Integer max);
}
