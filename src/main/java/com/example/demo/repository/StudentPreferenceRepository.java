package com.example.demo.repository;

import com.example.demo.entity.StudentPreference;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentPreferenceRepository extends JpaRepository<StudentPreference, Long> {

    // Derived query: find all preferences of a student ordered by rank
    List<StudentPreference> findByStudentIdOrderByRankAsc(Long studentId);

    // JPQL query: get preferences by course
    @Query("SELECT sp FROM StudentPreference sp WHERE sp.course.id = :courseId ORDER BY sp.rank ASC")
    List<StudentPreference> findByCourseIdOrdered(@Param("courseId") Long courseId);

    // Modifying query: delete all preferences for a student
    @Modifying
    @Transactional
    @Query("DELETE FROM StudentPreference sp WHERE sp.student.id = :studentId")
    void deleteAllByStudentId(@Param("studentId") Long studentId);
}
