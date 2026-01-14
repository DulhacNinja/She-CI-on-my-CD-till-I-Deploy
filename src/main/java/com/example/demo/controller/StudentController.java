package com.example.demo.controller;

import com.example.demo.dto.StudentDTO;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(
        name = "Students",
        description = "Operations related to students management"
)
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(
            summary = "Create a new student",
            description = "Adds a new student to the system (Admin only)",
//            security = @SecurityRequirement(name = "Bearer Authentication"),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Student created successfully",
                            content = @Content(schema = @Schema(implementation = StudentDTO.class))
                    ),
                    @ApiResponse(responseCode = "403", description = "Access denied - Admin only")
            }
    )
    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO saved = studentService.createStudent(studentDTO);
        return ResponseEntity.ok(saved);
    }

    @Operation(
            summary = "Get all students",
            description = "Returns a list of all students (Public access)"
    )
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        System.out.println("Get all students but it's from the controller.");
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @Operation(
            summary = "Get a student by ID",
            description = "Fetches a specific student by ID (Public access)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Student found",
                            content = @Content(schema = @Schema(implementation = StudentDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Student not found")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    @Operation(
            summary = "Update a student",
            description = "Modifies details of an existing student (Admin only)"
//            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentDTO> updateStudent(
            @PathVariable Long id,
            @RequestBody StudentDTO updatedStudentDTO) {
        try {
            StudentDTO updated = studentService.updateStudent(id, updatedStudentDTO);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Delete a student",
            description = "Removes a student from the system (Admin only)"
//            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Get students by year",
            description = "Returns students filtered by their year of study (Public access)"
    )
    @GetMapping("/year/{year}")
    public ResponseEntity<List<StudentDTO>> getStudentsByYear(@PathVariable Integer year) {
        List<StudentDTO> filtered = studentService.getAllStudents().stream()
                .filter(s -> s.year().equals(year))
                .toList();
        return ResponseEntity.ok(filtered);
    }
}