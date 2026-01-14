//package com.example.demo;
//
//import com.example.demo.dto.StudentDTO;
//import com.example.demo.entity.*;
//import com.example.demo.service.*;
//import com.github.javafaker.Faker;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import java.text.Normalizer;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Generator {
//    public List<Instructor> generateInstructors(Faker faker, InstructorService instructorService) {
//        List<Instructor> instructors = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            String first = faker.name().firstName();
//            String last = faker.name().lastName();
//            String fullName = first + " " + last;
//
//            String username = (first + "_" + last).toLowerCase();
//            String email = generateEmail(first, last, "university.edu");
//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//            String password = encoder.encode("password123"); // or a faker-generated password
//
//            Instructor instructor = Instructor.builder()
//                    .username(username)
//                    .password(password)
//                    .email(email)
//                    .role(Role.INSTRUCTOR)
//                    .name(fullName)
//                    .build();
//
//            instructors.add(instructorService.createInstructor(instructor));
//        }
//
//        System.out.println("Created " + instructors.size() + " instructors.");
//        return instructors;
//    }
//
//    public List<Pack> generatePacks(Faker faker, PackService packService) {
//        List<Pack> packs = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            Pack pack = Pack.builder()
//                    .year(faker.number().numberBetween(1, 3))
//                    .semester(faker.number().numberBetween(1, 2))
//                    .name("Pack " + faker.book().title())
//                    .build();
//            packs.add(packService.createPack(pack));
//        }
//        System.out.println("Created " + packs.size() + " packs.");
//        return packs;
//    }
//
//    public List<Course> generateCourses(Faker faker, CourseService courseService, List<Instructor> instructors, List<Pack> packs) {
//        List<Course> courses = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            Instructor instructor = instructors.get(faker.random().nextInt(instructors.size()));
//            Pack pack = packs.get(faker.random().nextInt(packs.size()));
//            if(faker.number().numberBetween(1, 5) <= 3) {
//                pack = null;
//            }
//            Course course = Course.builder()
//                    .type(faker.options().option("Lecture", "Lab", "Seminar"))
//                    .code("CSE" + faker.number().digits(3))
//                    .abbr(faker.educator().course().substring(0, Math.min(5, faker.educator().course().length())))
//                    .name(faker.educator().course())
//                    .instructor(instructor)
//                    .pack(pack)
//                    .groupCount(faker.number().numberBetween(1, 5))
//                    .description(faker.lorem().sentence(10))
//                    .build();
//            courses.add(courseService.createCourse(course));
//        }
//        System.out.println("Created " + courses.size() + " courses.");
//        return courses;
//    }
//
//    public List<StudentDTO> generateStudents(Faker faker, StudentService studentService) {
//        List<StudentDTO> studentDTOs = new ArrayList<>();
//        for (int i = 0; i < 15; i++) {
//            String first = faker.name().firstName();
//            String last = faker.name().lastName();
//            String fullName = first + " " + last;
//
//            String username = (first + "_" + last).toLowerCase();
//            String email = generateEmail(first, last, "student.university.edu");
//
//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//            String password = encoder.encode("password123"); // or a faker-generated password
//
//            Student student = Student.builder()
//                    .username(username)
//                    .password(password)
//                    .email(email)
//                    .role(Role.STUDENT)
//                    .name(fullName)
//                    .code("STU" + faker.number().digits(4))
//                    .year(faker.number().numberBetween(1, 4))
//                    .build();
//
//            StudentDTO dto = studentService.toDTO(student);
//            studentDTOs.add(studentService.createStudent(dto));
//        }
//
//        System.out.println("Created " + studentDTOs.size() + " students.");
//        return studentDTOs;
//    }
//    public List<Admin> generateAdmins(Faker faker) {
//        List<Admin> admins = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            String first = faker.name().firstName();
//            String last = faker.name().lastName();
//            String fullName = first + " " + last;
//
//            String username = (first + "_" + last).toLowerCase();
//            String email = generateEmail(first, last, "university.edu");
//
//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//            String password = "password123";
//
//            Admin admin = Admin.builder()
//                    .username(username)
//                    .password(password)
//                    .email(email)
//                    .role(Role.ADMIN)
//                    .name(fullName)
//                    .build();
////            admins.add(adminService.createAdmin(admin));
//        }
//        return admins;
//    }
//    public String generateEmail(String first, String last, String domain) {
//        String cleanFirst = normalize(first).toLowerCase();
//        String cleanLast = normalize(last).toLowerCase();
//        return cleanFirst + "." + cleanLast + "@" + domain;
//    }
//
//    public String normalize(String input) {
//        return Normalizer.normalize(input, Normalizer.Form.NFD)
//                .replaceAll("[^\\p{ASCII}]", "")
//                .replaceAll("[^a-zA-Z]", "");
//    }
//}
