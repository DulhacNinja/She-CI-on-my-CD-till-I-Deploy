package com.example.demo.dto;

public record InstructorPreferenceResponseDTO(
        Long id,
        Long optionalCourseId,
        String optionalCourseCode,
        Long instructorId,
        String instructorName,
        String compulsoryCourseAbbr,
        Integer percentage
) {}
