package com.example.demo.dto;

public record InstructorPreferenceRequestDTO(
        Long optionalCourseId,
        Long instructorId,
        String compulsoryCourseAbbr,
        Integer percentage
) {}
