package com.example.demo.dto;

public record GradeDTO(
        Long id,
        Double numericValue,
        Long studentId,
        Long courseId
) {}
