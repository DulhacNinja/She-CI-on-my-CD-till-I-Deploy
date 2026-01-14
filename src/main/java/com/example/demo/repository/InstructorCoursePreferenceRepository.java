package com.example.demo.repository;

import com.example.demo.entity.InstructorCoursePreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructorCoursePreferenceRepository
        extends JpaRepository<InstructorCoursePreference, Long> {

    List<InstructorCoursePreference> findByInstructorId(Long instructorId);

    List<InstructorCoursePreference> findByOptionalCourseId(Long courseId);
}
