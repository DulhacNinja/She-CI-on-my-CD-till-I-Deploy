package com.example.demo;

import com.example.demo.dto.AssignmentDTO;
import com.example.demo.dto.MatchingCourseDTO;
import com.example.demo.dto.MatchingStudentDTO;
import com.example.demo.dto.StableMatchingRequestDTO;
import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.MatchingClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.javapoet.ClassName;
import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.client.WebClient;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class StableMatchingRunner implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final MatchingClientService matchingClientService;
    private final Counter stableMatchingHttpCounter;
    private final Timer stableMatchingTimer;
    private static final Logger log = LoggerFactory.getLogger(StableMatchingRunner.class);

    public StableMatchingRunner(
            StudentRepository studentRepository,
            CourseRepository courseRepository,
            MatchingClientService matchingClientService,
            MeterRegistry meterRegistry
    ) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.matchingClientService = matchingClientService;

        this.stableMatchingHttpCounter = Counter.builder("stable.match.http.calls")
                .description("HTTP calls made to stable matching service by runner")
                .register(meterRegistry);
        this.stableMatchingTimer = Timer.builder("stable.matching.response.time")
                .description("Response time of Stable Matching algorithm")
                .publishPercentiles(0.5, 0.95, 0.99)
                .register(meterRegistry);
    }

    @Override
    public void run(String... args) {
            List<MatchingStudentDTO> students = studentRepository.findAll()
                    .stream()
                    .map(s -> new MatchingStudentDTO(s.getId(), s.getCode()))
                    .toList();

            List<Course> optionalCourses = courseRepository.findAll()
                    .stream()
                    .filter(c -> c.getPack() != null)
                    .toList();

            Map<Long, List<MatchingCourseDTO>> coursesByPack =
                    optionalCourses.stream()
                            .collect(Collectors.groupingBy(
                                    c -> c.getPack().getId(),
                                    Collectors.mapping(
                                            c -> new MatchingCourseDTO(
                                                    c.getId(),
                                                    c.getCode(),
                                                    c.getPack().getId()
                                            ),
                                            Collectors.toList()
                                    )
                            ));

            coursesByPack.forEach((packId, courseDtos) -> {

                StableMatchingRequestDTO request =
                        new StableMatchingRequestDTO(students, courseDtos);

                log.info("Sending matching request for PACK: {}", packId);


                stableMatchingTimer.record(() -> {
                    try {

                        var assignments = matchingClientService.runMatching(request).get();
                        stableMatchingHttpCounter.increment();


                        log.info("Assignments for pack {}:", packId);
                        assignments.forEach(a -> log.info("{}", a));

                    } catch (Exception e) {
                        log.error("Failed to run stable matching for pack {}", packId, e);
                    }
                });

                try {
                    matchingClientService.deleteAssignments().get();
                    log.info("Matching assignments deleted for pack {}", packId);
                } catch (Exception e) {
                    log.error("Failed to delete matching assignments for pack {}", packId, e);
                }
            });
        }
}