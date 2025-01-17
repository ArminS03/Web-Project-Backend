package com.project.web.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.project.web.Entity.Answer;

public interface AnswerRepository extends CrudRepository<Answer, Integer> {

    @Query("SELECT * FROM answers WHERE user_id = :userId")
    List<Answer> findAllByUserId(Integer userId);
    
    Optional<Answer> findByQuestionIdAndUserId(Integer questionId, Integer userId);
    // boolean existsByQuestionIdAndUserId(Integer questionId, Integer userId);
}
