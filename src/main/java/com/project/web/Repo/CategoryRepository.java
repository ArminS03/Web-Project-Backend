package com.project.web.Repo;

import org.springframework.data.repository.CrudRepository;

import com.project.web.Entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
