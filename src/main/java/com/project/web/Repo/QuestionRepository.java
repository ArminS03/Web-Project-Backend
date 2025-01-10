package com.project.web.Repo;

import org.springframework.data.repository.CrudRepository;

import com.project.web.Entity.Question;

public interface QuestionRepository extends CrudRepository<Question, Integer> {
}
