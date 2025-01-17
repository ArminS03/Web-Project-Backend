package com.project.web.Repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.project.web.Entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
