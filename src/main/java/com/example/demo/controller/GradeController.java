package com.example.demo.controller;

import com.example.demo.entity.Grade;
import com.example.demo.service.GradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    // GET all grades
    @GetMapping
    public ResponseEntity<List<Grade>> getAllGrades() {
        return ResponseEntity.ok(gradeService.getAllGrades());
    }

    // GET grades by student code
    @GetMapping("/student/{studentCode}")
    public ResponseEntity<List<Grade>> getGradesByStudent(@PathVariable String studentCode) {
        return ResponseEntity.ok(gradeService.getGradesByStudentCode(studentCode));
    }

    // GET grades by course code
    @GetMapping("/course/{courseCode}")
    public ResponseEntity<List<Grade>> getGradesByCourse(@PathVariable String courseCode) {
        return ResponseEntity.ok(gradeService.getGradesByCourseCode(courseCode));
    }
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<String> uploadGradesCsv(@RequestParam("file") MultipartFile file) {
        try {
            gradeService.importGradesFromCsv(file);
            return ResponseEntity.ok("CSV processed successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to process CSV: " + e.getMessage());
        }
    }
}
