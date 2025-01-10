package com.project.web.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jdbc.repository.query.Query;
import java.util.List;

import com.project.web.Entity.Answer;

public interface AnswerRepository extends CrudRepository<Answer, Integer> {

    @Query("SELECT * FROM answers WHERE user_id = :userId")
    List<Answer> findAllByUserId(Integer userId);
}
