package com.example.blogsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "username is empty")
    @Size(min = 4,message = "username must be at least 4 characters or more ")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String username;

    @NotEmpty(message = "password is empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;

    @NotEmpty(message = "email is empty")
    @Email
    @Column(columnDefinition = "varchar(30) not null unique")
    private String email;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime registrationDate;



}
