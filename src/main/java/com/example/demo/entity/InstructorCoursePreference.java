package com.example.demo.entity;

import com.example.demo.entity.Course;
import com.example.demo.entity.Instructor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "instructor_course_preferences")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstructorCoursePreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // optional course (must have pack != null)
    @ManyToOne(optional = false)
    @JoinColumn(name = "optional_course_id")
    private Course optionalCourse;

    @ManyToOne(optional = false)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @Column(name = "compulsory_course_abbr", nullable = false)
    private String compulsoryCourseAbbr;

    @Column(nullable = false)
    private Integer percentage;
}
