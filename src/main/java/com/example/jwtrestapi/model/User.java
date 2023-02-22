package com.example.jwtrestapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity{
    @Column(name = "username")
    private String username;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    @ManyToMany     // связь через промежут таблицу "user_roles"
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},  // 1 колонка
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})  // 2 колонка
    private List<Role> roles;
}
