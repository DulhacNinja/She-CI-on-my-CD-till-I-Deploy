package com.example.demo.service;

import com.example.demo.entity.StudentPreference;
import com.example.demo.repository.StudentPreferenceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentPreferenceService {

    private final StudentPreferenceRepository repository;

    public StudentPreferenceService(StudentPreferenceRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public StudentPreference createPreference(StudentPreference preference) {
        return repository.save(preference);
    }

    // READ ALL
    public List<StudentPreference> getAllPreferences() {
        return repository.findAll();
    }

    // READ ONE
    public Optional<StudentPreference> getPreferenceById(Long id) {
        return repository.findById(id);
    }

    // READ BY STUDENT
    public List<StudentPreference> getPreferencesByStudent(Long studentId) {
        return repository.findByStudentIdOrderByRankAsc(studentId);
    }

    // READ BY COURSE
    public List<StudentPreference> getPreferencesByCourse(Long courseId) {
        return repository.findByCourseIdOrdered(courseId);
    }

    // UPDATE
    public StudentPreference updatePreference(Long id, StudentPreference updatedPreference) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setRank(updatedPreference.getRank());
                    existing.setStudent(updatedPreference.getStudent());
                    existing.setCourse(updatedPreference.getCourse());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Preference not found with id " + id));
    }

    // DELETE
    public void deletePreference(Long id) {
        repository.deleteById(id);
    }

    // DELETE ALL by Student
    @Transactional
    public void deleteAllForStudent(Long studentId) {
        repository.deleteAllByStudentId(studentId);
    }
}
