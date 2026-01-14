package com.example.demo.listener;

import com.example.demo.entity.Grade;
import com.example.demo.entity.Student;
import com.example.demo.entity.Course;
import com.example.demo.service.StudentService;
import com.example.demo.service.CourseService;
import com.example.demo.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageListener {

    private final StudentService studentService;
    private final CourseService courseService;
    private final GradeRepository gradeRepository;

    @JmsListener(destination = "grades-queue")
    public void listen(String message) {

        try {
            // expected: studentCode,courseCode,grade
            String[] parts = message.split(",");

            String studentCode = parts[0].trim();
            String courseCode  = parts[1].trim();
            Double value       = Double.parseDouble(parts[2].trim());

            Student student = studentService.findByCode(studentCode)
                    .orElseThrow(() ->
                            new IllegalArgumentException("Student not found: " + studentCode));

            Course course = courseService.findByCode(courseCode)
                    .orElseThrow(() ->
                            new IllegalArgumentException("Course not found: " + courseCode));

            Grade grade = Grade.builder()
                    .studentId(student.getId())
                    .courseId(course.getId())
                    .numericValue(value)
                    .build();

            gradeRepository.save(grade);

            System.out.println("Saved grade: " + grade.getStudentId() + "\n" +
                    grade.getCourseId() + "\n" + grade.getNumericValue());

        } catch (Exception e) {
            System.err.println("Failed to process message: " + message);
            e.printStackTrace();
        }
    }
}
