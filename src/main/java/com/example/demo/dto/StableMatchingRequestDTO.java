package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public record StableMatchingRequestDTO(
        List<MatchingStudentDTO> students,
        List<MatchingCourseDTO> courses
) {}
