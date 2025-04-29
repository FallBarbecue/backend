package com.example.demo.entity;

import com.example.demo.entity.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
//    private byte[] profilePicture;

    @Enumerated(EnumType.STRING)
    private Role role;
}