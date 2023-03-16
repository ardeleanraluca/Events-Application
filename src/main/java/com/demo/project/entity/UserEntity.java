package com.demo.project.entity;

import com.demo.project.util.UserType;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UserType userType;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String dateOfBirth;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String county;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;


}