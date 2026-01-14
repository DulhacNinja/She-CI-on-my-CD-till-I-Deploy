package com.example.demo.service;

import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // ---------------------------
    //  Mapping Helpers
    // ---------------------------
    public StudentDTO toDTO(Student student) {
        return new StudentDTO(
                student.getId(),
                student.getUsername(),
                student.getPassword(),
                student.getEmail(),
                student.getName(),
                student.getCode(),
                student.getYear(),
                student.getRole()
        );
    }

    public Student toEntity(StudentDTO dto) {
        return Student.builder()
                .id(dto.id())
                .username(dto.username())
                .password(dto.password())
                .email(dto.email())
                .name(dto.name())
                .code(dto.code())
                .year(dto.year())
                .role(dto.role())
                .build();
    }

    // ---------------------------
    //  CREATE
    // ---------------------------
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = toEntity(studentDTO);
        Student saved = studentRepository.save(student);
        return toDTO(saved);
    }

    // ---------------------------
    //  READ ALL
    // ---------------------------
    public List<StudentDTO> getAllStudents() {
        System.out.println("Get all students but it's from the service");
        return studentRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // ---------------------------
    //  READ ONE
    // ---------------------------
    public Optional<StudentDTO> getStudentById(Long id) {
        return studentRepository.findById(id)
                .map(this::toDTO);
    }

    // ---------------------------
    //  UPDATE
    // ---------------------------
    public StudentDTO updateStudent(Long id, StudentDTO updatedStudentDTO) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        existing.setId(updatedStudentDTO.id());
        existing.setCode(updatedStudentDTO.code());
        existing.setName(updatedStudentDTO.name());
        existing.setEmail(updatedStudentDTO.email());
        existing.setYear(updatedStudentDTO.year());

        Student saved = studentRepository.save(existing);
        return toDTO(saved);
    }

    // ---------------------------
    //  DELETE
    // ---------------------------
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Optional<Student> getStudentByUserUsername(String username) {
        return studentRepository.findByUsername(username);
    }

    public Optional<Student> findByCode(String studentCode) {
        return studentRepository.findByCode(studentCode);
    }
}
