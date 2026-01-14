package com.example.demo.service;

import com.example.demo.dto.InstructorPreferenceRequestDTO;
import com.example.demo.dto.InstructorPreferenceResponseDTO;
import com.example.demo.entity.InstructorCoursePreference;
import com.example.demo.repository.InstructorCoursePreferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorCoursePreferenceService {

    private final InstructorCoursePreferenceRepository repo;
    private final CourseService courseService;
    private final InstructorService instructorService;

    public InstructorPreferenceResponseDTO create(InstructorPreferenceRequestDTO dto) {

        var course = courseService.getCourseById(dto.optionalCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        System.out.println(dto);
        System.out.println(course);

        // IMPORTANT RULE — must be optional
        if (course.getPack() == null) {
            throw new IllegalArgumentException(
                    "Preferences can only be defined for OPTIONAL courses (course.pack_id must NOT be null)"
            );
        }

        var instructor = instructorService.getInstructorById(dto.instructorId())
                .orElseThrow(() -> new IllegalArgumentException("Instructor not found"));


        System.out.println(instructor);


        var pref = InstructorCoursePreference.builder()
                .optionalCourse(course)
                .instructor(instructor)
                .compulsoryCourseAbbr(dto.compulsoryCourseAbbr())
                .percentage(dto.percentage())
                .build();

        System.out.println(pref);

        var saved = repo.save(pref);

        System.out.println(saved);

        return toDTO(saved);
    }

    public List<InstructorPreferenceResponseDTO> getAll() {
        return repo.findAll().stream().map(this::toDTO).toList();
    }

    public List<InstructorPreferenceResponseDTO> getByInstructor(Long id) {
        return repo.findByInstructorId(id).stream().map(this::toDTO).toList();
    }

    public List<InstructorPreferenceResponseDTO> getByCourse(Long id) {
        return repo.findByOptionalCourseId(id).stream().map(this::toDTO).toList();
    }

    private InstructorPreferenceResponseDTO toDTO(InstructorCoursePreference pref) {
        return new InstructorPreferenceResponseDTO(
                pref.getId(),
                pref.getOptionalCourse().getId(),
                pref.getOptionalCourse().getCode(),
                pref.getInstructor().getId(),
                pref.getInstructor().getName(),
                pref.getCompulsoryCourseAbbr(),
                pref.getPercentage()
        );
    }
    public void delete(Long id) {
        repo.deleteById(id);
    }

}
