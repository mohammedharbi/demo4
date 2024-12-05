package com.example.blogsystem.Controller;

import com.example.blogsystem.ApiResponse.ApiResponse;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        }
        userService.updateUser(id, user);
        return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));
    }
    @GetMapping("/find-user-email-containing/{email}")
    public ResponseEntity findUserEmailContaining(@PathVariable String email) {
        return ResponseEntity.ok().body(userService.findUserByEmailContaining(email));
    }

    @GetMapping("/find-username-betweenminandmax/min/{min}/max/{max}")
    public ResponseEntity findUsernameBetweenMinAndMax(@PathVariable Integer min, @PathVariable Integer max) {
        return ResponseEntity.ok().body(userService.findUserByUsernameBetween(min, max));
    }
}