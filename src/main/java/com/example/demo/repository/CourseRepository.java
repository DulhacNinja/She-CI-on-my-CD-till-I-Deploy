package com.example.demo.repository;

import com.example.demo.entity.Course;
import com.example.demo.entity.Pack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCode(String code);

    @Transactional
    @Modifying
    @Query("UPDATE Course c SET c.groupCount = :groupCount WHERE c.id = :id")
    int updateGroupCount(@Param("id") Long id, @Param("groupCount") Integer groupCount);
}
