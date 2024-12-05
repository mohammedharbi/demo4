package com.example.blogsystem.Service;

import com.example.blogsystem.ApiResponse.ApiException;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User>  getAllUsers() {
        return userRepository.findAll();
    }
    public void addUser(User user) {
        userRepository.save(user);
    }
    public void updateUser(Integer id,User user) {
        User oldUser = userRepository.findUserById(id);
        if(oldUser != null) {
            oldUser.setUsername(user.getUsername());
            oldUser.setPassword(user.getPassword());
            oldUser.setEmail(user.getEmail());
            userRepository.save(oldUser);

        }else throw new ApiException("User not updated, user not found");
    }
    public void deleteUser(Integer id) {
        User oldUser = userRepository.findUserById(id);
        if(oldUser != null) {
            userRepository.delete(oldUser);
        }else throw new ApiException("User not deleted, user not found");
    }

    public User findUserByEmailContaining(String email) {
        return userRepository.findUserByEmailContaining(email);
    }

    public List<User> findUserByUsernameBetween(Integer min, Integer max){
        return userRepository.findUserByUsernameBetween(min, max);
    }

}
