//package com.example.demo.service;
//
//import com.example.demo.entity.Admin;
//import com.example.demo.repository.AdminRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class AdminService {
//
//    private final AdminRepository adminRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public Admin createAdmin(Admin admin) {
//        // Encode password before saving
//        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
//        return adminRepository.save(admin);
//    }
//
//    public Optional<Admin> getAdminById(Long id) {
//        return adminRepository.findById(id);
//    }
//
//    public Optional<Admin> getAdminByUsername(String username) {
//        return adminRepository.findByUsername(username);
//    }
//
//    public List<Admin> getAllAdmins() {
//        return adminRepository.findAll();
//    }
//
//    public Admin updateAdmin(Long id, Admin adminDetails) {
//        Admin admin = adminRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));
//
//        admin.setUsername(adminDetails.getUsername());
//        admin.setName(adminDetails.getName());
//        admin.setEmail(adminDetails.getEmail());
//
//        // Only update password if provided
//        if (adminDetails.getPassword() != null && !adminDetails.getPassword().isEmpty()) {
//            admin.setPassword(passwordEncoder.encode(adminDetails.getPassword()));
//        }
//
//        return adminRepository.save(admin);
//    }
//
//    public void deleteAdmin(Long id) {
//        adminRepository.deleteById(id);
//    }
//}