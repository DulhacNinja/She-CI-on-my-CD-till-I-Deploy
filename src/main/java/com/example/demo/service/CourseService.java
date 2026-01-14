package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public Course updateCourse(Long id, Course updatedCourse) {
        return courseRepository.findById(id)
                .map(course -> {
                    course.setType(updatedCourse.getType());
                    course.setCode(updatedCourse.getCode());
                    course.setAbbr(updatedCourse.getAbbr());
                    course.setName(updatedCourse.getName());
                    course.setInstructor(updatedCourse.getInstructor());
                    course.setPack(updatedCourse.getPack());
                    course.setGroupCount(updatedCourse.getGroupCount());
                    course.setDescription(updatedCourse.getDescription());
                    return courseRepository.save(course);
                })
                .orElseThrow(() -> new RuntimeException("Course not found with id " + id));
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public Optional<Course> findByCode(String courseCode) {
        return courseRepository.findByCode(courseCode);
    }
}
