package com.xdu.dzsb.repository;

import com.xdu.dzsb.model.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: wjy
 * @date: 2021/3/1
 * @description:
 */
@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
    
    List<Exercise> findAllByUserId(Integer userId);
}
