package com.example.demo;

import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import com.example.demo.Testing;
import java.text.Normalizer;
import java.util.*;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner loadFakeData(
//			StudentService studentService,
//			InstructorService instructorService,
//			PackService packService,
//			CourseService courseService,
//			AdminService adminService
//	) {
//		return args -> {
////			Generator generator = new Generator();
////			Faker faker = new Faker(new Locale("en"));
////			List<Instructor> instructors = generator.generateInstructors(faker, instructorService);
////			List<Pack> packs = generator.generatePacks(faker, packService);
////			List<Course> courses = generator.generateCourses(faker, courseService, instructors, packs);
////			List<StudentDTO> studentDTOs = generator.generateStudents(faker, studentService);
////			List<Admin> admins = generator.generateAdmins(faker, adminService);
//
////			Testing testing = new Testing();
////			Instructor instructor = testing.createInstructor(instructorService);
////			Pack pack = testing.createPack(packService);
////			Course course = testing.createCourse(courseService, instructor, pack);
////			StudentDTO studentDTO = testing.createStudent(studentService);
////
////			testing.readEntities(studentService, courseService, instructorService, packService);
////			testing.updateEntities(studentService, courseService, instructorService, packService, studentDTO, course, instructor, pack);
////			testing.deleteEntities(studentService, courseService, instructorService, packService, studentDTO, course, instructor, pack);
//
//		};
//	}

}
