//package com.example.demo.controller;
//
//import com.example.demo.dto.AuthRequest;
//import com.example.demo.dto.AuthResponse;
//import com.example.demo.entity.User;
//import com.example.demo.repository.UserRepository;
//import com.example.demo.security.JwtUtil;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//@RequiredArgsConstructor
//public class AuthController {
//
//    private final AuthenticationManager authenticationManager;
//    private final JwtUtil jwtUtil;
//    private final UserRepository userRepository;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
//        try {
//            // Authenticate user
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            authRequest.getUsername(),
//                            authRequest.getPassword()
//                    )
//            );
//
//            // Get user details
//            User user = userRepository.findByUsername(authRequest.getUsername())
//                    .orElseThrow(() -> new RuntimeException("User not found"));
//
//            // Generate JWT token
//            String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());
//
//            // Build response
//            AuthResponse response = AuthResponse.builder()
//                    .token(token)
//                    .username(user.getUsername())
//                    .role(user.getRole().name())
//                    .name(user.getName())
//                    .email(user.getEmail())
//                    .build();
//
//            return ResponseEntity.ok(response);
//
//        } catch (BadCredentialsException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body("Invalid username or password");
//        } catch (Exception e) {
//            System.out.println(e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("An error occurred during authentication");
//        }
//    }
//
//    @GetMapping("/me")
//    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
//        if (authentication == null || !authentication.isAuthenticated()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
//        }
//
//        User user = userRepository.findByUsername(authentication.getName())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        return ResponseEntity.ok(AuthResponse.builder()
//                .username(user.getUsername())
//                .role(user.getRole().name())
//                .name(user.getName())
//                .email(user.getEmail())
//                .build());
//    }
//}