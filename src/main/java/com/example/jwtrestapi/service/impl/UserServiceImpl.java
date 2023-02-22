package com.example.jwtrestapi.service.impl;

import com.example.jwtrestapi.model.Role;
import com.example.jwtrestapi.model.Status;
import com.example.jwtrestapi.model.User;
import com.example.jwtrestapi.repository.RoleRepository;
import com.example.jwtrestapi.repository.UserRepository;
import com.example.jwtrestapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));   // кодируем пароль
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);
        log.info("IN UserServiceImpl - register user: {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        log.info("IN UserServiceImpl - getAll - {} users found", users);
        return users;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);
        if(result==null) {
            log.warn("IN UserServiceImpl - findById - no user found by id: {}", id);
            return null;
        }
        log.info("IN UserServiceImpl - findById - user: {} found by id: {}", result, id);
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("IN UserServiceImpl - findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public void deleted(Long id) {
        userRepository.deleteById(id);
        log.info("IN UserServiceImpl - deleted - user with id: {} successfully deleted", id);
    }
}
