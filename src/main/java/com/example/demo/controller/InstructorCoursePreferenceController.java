package com.example.demo.controller;

import com.example.demo.dto.InstructorPreferenceRequestDTO;
import com.example.demo.dto.InstructorPreferenceResponseDTO;
import com.example.demo.service.InstructorCoursePreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructor-preferences")
@RequiredArgsConstructor
public class InstructorCoursePreferenceController {

    private final InstructorCoursePreferenceService service;

    // CREATE
    @PostMapping
//    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public ResponseEntity<InstructorPreferenceResponseDTO> create(
            @RequestBody InstructorPreferenceRequestDTO dto
    ) {
        System.out.println(dto);
        return ResponseEntity.ok(service.create(dto));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<InstructorPreferenceResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // GET BY INSTRUCTOR
    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<List<InstructorPreferenceResponseDTO>> getByInstructor(
            @PathVariable Long instructorId
    ) {
        return ResponseEntity.ok(service.getByInstructor(instructorId));
    }

    // GET BY OPTIONAL COURSE
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<InstructorPreferenceResponseDTO>> getByCourse(
            @PathVariable Long courseId
    ) {
        return ResponseEntity.ok(service.getByCourse(courseId));
    }

    // DELETE ONE
    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
