package com.project.course.backend.module.user.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

}
