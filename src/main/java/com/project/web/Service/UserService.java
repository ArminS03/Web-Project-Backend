package com.project.web.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.project.web.Entity.User;
import com.project.web.Repo.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUserNamesAndScores() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
            .peek(user -> {
                user.setId(null);
                user.setPassword(null);
            })
            .collect(Collectors.toList());
    }
}