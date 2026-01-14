package com.example.demo.mapper;

import com.example.demo.dto.GradeDTO;
import com.example.demo.entity.Course;
import com.example.demo.entity.Grade;
import com.example.demo.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class GradeMapper {

    public GradeDTO toDTO(Grade grade) {
        return new GradeDTO(
                grade.getId(),
                grade.getNumericValue(),
                grade.getStudentId(),
                grade.getCourseId()
        );
    }

    public Grade toEntity(GradeDTO dto, Long student, Long course) {
        return Grade.builder()
                .id(dto.id())
                .numericValue(dto.numericValue())
                .studentId(student)
                .courseId(course)
                .build();
    }
}
