package com.example.demo.test;

import com.example.demo.controller.StudentController;
import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Role;
import com.example.demo.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = StudentController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService studentService;

    @Test
    public void testGetAllStudents() throws Exception {
        // Arrange
        List<StudentDTO> students = List.of(
                new StudentDTO(1L, "john123", "pass123", "john@example.com",
                        "John Doe", "STU001", 2, Role.STUDENT),
                new StudentDTO(2L, "jane456", "pass456", "jane@example.com",
                        "Jane Smith", "STU002", 3, Role.STUDENT)
        );
        when(studentService.getAllStudents()).thenReturn(students);

        // Act & Assert
        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].code").value("STU001"))
                .andExpect(jsonPath("$[1].name").value("Jane Smith"))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetStudentById_ThrowsException_WhenStudentNotFound() throws Exception {
        // Arrange
        Long nonExistentId = 999L;
        when(studentService.getStudentById(nonExistentId)).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(get("/api/students/{id}", nonExistentId))
                .andExpect(status().isNotFound());
    }
}