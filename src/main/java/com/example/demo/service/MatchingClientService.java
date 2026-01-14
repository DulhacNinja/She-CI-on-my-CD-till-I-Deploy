package com.example.demo.service;

import com.example.demo.dto.AssignmentDTO;
import com.example.demo.dto.StableMatchingRequestDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class MatchingClientService {

    private final RestTemplate restTemplate;

    @TimeLimiter(name = "matchingService")
    @CircuitBreaker(name = "matchingService", fallbackMethod = "fallbackAssignments")
    public CompletableFuture<List<AssignmentDTO>> runMatching(StableMatchingRequestDTO request) {

        return CompletableFuture.supplyAsync(() -> {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));

            HttpEntity<StableMatchingRequestDTO> entity =
                    new HttpEntity<>(request, headers);

            ResponseEntity<AssignmentDTO[]> response =
                    restTemplate.exchange(
                            "http://stable-matching-service/api/matching/run",
                            HttpMethod.POST,
                            entity,
                            AssignmentDTO[].class
                    );

            return List.of(response.getBody());
        });
    }


    public CompletableFuture<List<AssignmentDTO>> fallbackAssignments(
            StableMatchingRequestDTO request,
            Throwable throwable) {

        System.out.println("Fallback triggered: " + throwable.getMessage());
        return CompletableFuture.completedFuture(List.of());
    }


    @Retry(name = "delete")
    @CircuitBreaker(name = "delete", fallbackMethod = "fallbackDelete")
    public CompletableFuture<ResponseEntity<Void>> deleteAssignments() {

        return CompletableFuture.supplyAsync(() -> {
            restTemplate.delete("http://stable-matching-service/api/matching");
            return ResponseEntity.ok().build();
        });
    }

    public CompletableFuture<ResponseEntity<Void>> fallbackDelete(Throwable throwable) {
        System.out.println("Fallback triggered on delete: " + throwable.getMessage());
        return CompletableFuture.completedFuture(
                ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build()
        );
    }
}
