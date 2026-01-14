package com.example.demo.service;

import com.example.demo.entity.Instructor;
import com.example.demo.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public Instructor createInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public Optional<Instructor> getInstructorById(Long id) {
        return instructorRepository.findById(id);
    }

    public Instructor updateInstructor(Long id, Instructor updatedInstructor) {
        return instructorRepository.findById(id)
                .map(instructor -> {
                    instructor.setName(updatedInstructor.getName());
                    instructor.setEmail(updatedInstructor.getEmail());
                    return instructorRepository.save(instructor);
                })
                .orElseThrow(() -> new RuntimeException("Instructor not found with id " + id));
    }

    public void deleteInstructor(Long id) {
        instructorRepository.deleteById(id);
    }
}
