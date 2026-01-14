package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.entity.Grade;
import com.example.demo.entity.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.GradeRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
public class GradeService {
    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    StudentService studentService;

    @Autowired
    CourseService courseService;

    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    public List<Grade> getGradesByStudentCode(String studentCode) {
        Student student = studentService.findByCode(studentCode)
                .orElseThrow(() ->
                        new IllegalArgumentException("Student not found: " + studentCode));

        Long studentId = student.getId();
        return gradeRepository.findByStudentId(studentId);
    }

    public List<Grade> getGradesByCourseCode(String courseCode) {
        Course course = courseService.findByCode(courseCode)
                .orElseThrow(() ->
                        new IllegalArgumentException("Course not found: " + courseCode));
        Long courseId = course.getId();

        return gradeRepository.findByCourseId(courseId);
    }

    public void importGradesFromCsv(MultipartFile file) {
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");

                String studentCode = parts[0].trim();
                String courseCode  = parts[1].trim();
                Double value       = Double.parseDouble(parts[2].trim());

                var student = studentService.findByCode(studentCode)
                        .orElseThrow(() -> new IllegalArgumentException("Student not found: " + studentCode));

                var course = courseService.findByCode(courseCode)
                        .orElseThrow(() -> new IllegalArgumentException("Course not found: " + courseCode));

                Grade grade = Grade.builder()
                        .studentId(student.getId())
                        .courseId(course.getId())
                        .numericValue(value)
                        .build();
                System.out.println("Importing grade: " + grade.getStudentId() + " " + grade.getCourseId() + " " + grade.getNumericValue());
                gradeRepository.save(grade);
                }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
