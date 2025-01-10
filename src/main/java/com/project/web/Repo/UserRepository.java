package com.project.web.Repo;

import org.springframework.data.repository.CrudRepository;

import com.project.web.Entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
