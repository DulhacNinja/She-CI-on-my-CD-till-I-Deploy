package com.example.demo.controller;

import com.example.demo.entity.StudentPreference;
import com.example.demo.service.StudentPreferenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preferences")
@Tag(name = "Student Preferences", description = "Operations related to student course preferences")
public class StudentPreferenceController {

    private final StudentPreferenceService service;

    public StudentPreferenceController(StudentPreferenceService service) {
        this.service = service;
    }

    @Operation(
            summary = "Create a preference",
            description = "Students can create their own preferences, admins can create for anyone",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @PreAuthorize("hasPermission(#preference, 'create')")
    @PostMapping
    public ResponseEntity<StudentPreference> createPreference(@RequestBody StudentPreference preference) {
        System.out.println(preference.toString());
        return ResponseEntity.ok(service.createPreference(preference));
    }

    @Operation(
            summary = "Get all preferences",
            description = "Returns a list of all preferences (Public access)"
    )
    @GetMapping
    public ResponseEntity<List<StudentPreference>> getAllPreferences() {
        return ResponseEntity.ok(service.getAllPreferences());
    }

    @Operation(
            summary = "Get a preference by ID",
            description = "Fetches a specific preference by ID (Public access)"
    )
    @GetMapping("/{id}")
    public ResponseEntity<StudentPreference> getPreferenceById(@PathVariable Long id) {
        return service.getPreferenceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Get preferences by student",
            description = "Returns all preferences for a specific student (Public access)"
    )
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<StudentPreference>> getPreferencesByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(service.getPreferencesByStudent(studentId));
    }

    @Operation(
            summary = "Get preferences by course",
            description = "Returns all preferences for a specific course (Public access)"
    )
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<StudentPreference>> getPreferencesByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(service.getPreferencesByCourse(courseId));
    }

    @Operation(
            summary = "Update a preference",
            description = "Students can update their own preferences, admins can update any",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @PreAuthorize("hasPermission(#id, 'StudentPreference', 'update')")
    @PutMapping("/{id}")
    public ResponseEntity<StudentPreference> updatePreference(
            @PathVariable Long id,
            @RequestBody StudentPreference updatedPreference) {
        try {
            return ResponseEntity.ok(service.updatePreference(id, updatedPreference));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Delete a preference",
            description = "Students can delete their own preferences, admins can delete any",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @PreAuthorize("hasPermission(#id, 'StudentPreference', 'delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePreference(@PathVariable Long id) {
        service.deletePreference(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Delete all preferences for a student",
            description = "Students can delete their own preferences, admins can delete for anyone",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @PreAuthorize("hasPermission(#studentId, 'Student', 'deleteAllPreferences')")
    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<Void> deleteAllByStudent(@PathVariable Long studentId) {
        service.deleteAllForStudent(studentId);
        return ResponseEntity.noContent().build();
    }
}