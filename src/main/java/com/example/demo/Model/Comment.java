package com.example.blogsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "content is empty")
    @Size(min = 2, message = "content at least 2 characters or more")
    @Column(columnDefinition = "varchar(50) not null")
    private String content;

    @NotNull(message = "user id null")
    @Column(unique = true)
    private Integer userID;

    @NotNull(message = "user id null")
    @Column(unique = false, nullable = false)
    private Integer postID;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime commentDate;
}