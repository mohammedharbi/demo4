package com.example.blogsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "title is empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String title;

    @NotEmpty(message = "content is empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String content;

    @NotNull(message = "user id null")
    @Column(unique = false)
    private Integer userID;

    @NotNull(message = "category id null")
    @Column(unique = false)
    private Integer categoryID;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime publishDate;
}