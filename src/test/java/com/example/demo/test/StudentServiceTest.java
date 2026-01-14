package com.example.demo.test;

import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    public void testGetAllStudents() {
        // Arrange
        Student student1 = Student.builder()
                .id(1L)
                .username("john123")
                .password("pass123")
                .email("john@example.com")
                .name("John Doe")
                .code("STU001")
                .year(2)
                .role(Role.valueOf("STUDENT"))
                .build();

        Student student2 = Student.builder()
                .id(2L)
                .username("jane456")
                .password("pass456")
                .email("jane@example.com")
                .name("Jane Smith")
                .code("STU002")
                .year(3)
                .role(Role.valueOf("STUDENT"))
                .build();

        when(studentRepository.findAll()).thenReturn(List.of(student1, student2));

        // Act
        List<StudentDTO> result = studentService.getAllStudents();

        // Assert
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).name());
        assertEquals("Jane Smith", result.get(1).name());
        assertEquals("STU001", result.get(0).code());
    }

    @Test
    public void testUpdateStudent_ThrowsException_WhenStudentNotFound() {
        // Arrange
        Long nonExistentId = 999L;
        StudentDTO updatedStudentDTO = new StudentDTO(
                999L,
                "john123",
                "pass123",
                "john@example.com",
                "John Updated",
                "STU001",
                3,
                Role.STUDENT
        );

        when(studentRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            studentService.updateStudent(nonExistentId, updatedStudentDTO);
        });
        System.out.println(exception.getMessage());

        assertEquals("Student not found", exception.getMessage());
    }
}