//package com.example.demo;
//
//import com.example.demo.dto.StudentDTO;
//import com.example.demo.entity.Course;
//import com.example.demo.entity.Instructor;
//import com.example.demo.entity.Pack;
//import com.example.demo.entity.Student;
//import com.example.demo.service.CourseService;
//import com.example.demo.service.InstructorService;
//import com.example.demo.service.PackService;
//import com.example.demo.service.StudentService;
//import com.github.javafaker.Faker;
//
//import java.text.Normalizer;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Testing {
//
//    public Instructor createInstructor(InstructorService instructorService) {
//        Instructor instructor = Instructor.builder()
//                .name("Prof Profesorescu")
//                .email("prof.profesorescu.edu")
//                .build();
//        instructor = instructorService.createInstructor(instructor);
//        System.out.println("Created Instructor: " + instructor);
//        return instructor;
//    }
//
//    public Pack createPack(PackService packService) {
//        Pack pack = Pack.builder()
//                .year(3)
//                .semester(1)
//                .name("Pachet anu 3 aproape ai terminat faculta")
//                .build();
//        pack = packService.createPack(pack);
//        System.out.println("Created Pack: " + pack);
//        return pack;
//    }
//
//    public Course createCourse(CourseService courseService, Instructor instructor, Pack pack) {
//        Course course = Course.builder()
//                .type("Lecture")
//                .code("CS101")
//                .abbr("IntroCS")
//                .name("Introduction to Computer Science")
//                .instructor(instructor)
//                .pack(pack)
//                .groupCount(3)
//                .description("Fundamentals of computer science.")
//                .build();
//        course = courseService.createCourse(course);
//        System.out.println("Created Course: " + course);
//        return course;
//    }
//
//    public StudentDTO createStudent(StudentService studentService) {
//        Student student = Student.builder()
//                .code("STU670")
//                .name("Alis Student")
//                .email("alis.student@test.com")
//                .year(2)
//                .build();
//        StudentDTO studentDTO = studentService.toDTO(student);
//        StudentDTO createdStudentDTO = studentService.createStudent(studentDTO);
//        System.out.println("Created Student: " + studentDTO);
//        return createdStudentDTO;
//    }
//
//    // ------------------------------
//    // READ
//    // ------------------------------
//    public void readEntities(StudentService studentService, CourseService courseService, InstructorService instructorService, PackService packService) {
//        System.out.println("\nFetching all students:");
//        studentService.getAllStudents().forEach(System.out::println);
//
//        System.out.println("\nFetching all courses:");
//        courseService.getAllCourses().forEach(System.out::println);
//
//        System.out.println("\nFetching all instructors:");
//        instructorService.getAllInstructors().forEach(System.out::println);
//
//        System.out.println("\nFetching all packs:");
//        packService.getAllPacks().forEach(System.out::println);
//    }
//
//    // ------------------------------
//    // UPDATE
//    // ------------------------------
//    public void updateEntities(StudentService studentService, CourseService courseService, InstructorService instructorService, PackService packService, StudentDTO studentDTO, Course course, Instructor instructor, Pack pack ) {
//        System.out.println("\nUpdating Student...");
//        Student student = studentService.toEntity(studentDTO);
//        student.setYear(3);
//        studentDTO = studentService.toDTO(student);
//
//        System.out.println(studentDTO);
//        studentService.updateStudent(studentDTO.id(), studentDTO);
//        System.out.println("Updated Student: " + studentDTO);
//
//        System.out.println("\nUpdating Course group count...");
//        course.setGroupCount(4);
//        Course updatedCourse = courseService.updateCourse(course.getId(), course);
//        System.out.println("Updated Course: " + updatedCourse);
//
//        System.out.println("\nUpdating Instructor...");
//        instructor.setName("boss");
//        Instructor updatedInstructor = instructorService.updateInstructor(instructor.getId(), instructor);
//        System.out.println("Updated Instructor: " + updatedInstructor);
//
//        System.out.println("\nUpdating Pack...");
//        pack.setYear(1);
//        Pack updatedPack = packService.updatePack(pack.getId(), pack);
//        System.out.println("Updated Pack: " + updatedPack);
//
//    }
//
//    // ------------------------------
//    // DELETE
//    // ------------------------------
//    public void deleteEntities(StudentService studentService, CourseService courseService, InstructorService instructorService, PackService packService, StudentDTO studentDTO, Course course, Instructor instructor, Pack pack) {
//        System.out.println("\nDeleting Student...");
//        studentService.deleteStudent(studentDTO.id());
//        System.out.println("Student deleted.");
//
//        System.out.println("\nDeleting Course...");
//        courseService.deleteCourse(course.getId());
//        System.out.println("Course deleted.");
//
//        System.out.println("\nDeleting Instructor...");
//        instructorService.deleteInstructor(instructor.getId());
//        System.out.println("Instructor deleted.");
//
//        System.out.println("\nDeleting Pack...");
//        packService.deletePack(pack.getId());
//        System.out.println("Pack deleted.");
//
//    }
//}
