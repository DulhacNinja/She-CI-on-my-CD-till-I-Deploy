package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "grades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // REQUIRED numeric value
    @Column(nullable = false)
    private Double numericValue;

    @JoinColumn(name = "student_id", nullable = false)
    private Long studentId;

    @JoinColumn(name = "course_id", nullable = false)
    private Long courseId;
}
