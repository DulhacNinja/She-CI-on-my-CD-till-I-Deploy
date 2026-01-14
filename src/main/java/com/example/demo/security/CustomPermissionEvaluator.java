//package com.example.demo.security;
//
//import com.example.demo.entity.StudentPreference;
//import com.example.demo.repository.StudentPreferenceRepository;
//import com.example.demo.service.StudentPreferenceService;
//import com.example.demo.service.StudentService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.access.PermissionEvaluator;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//import java.io.Serializable;
//
//@Component
//@RequiredArgsConstructor
//public class CustomPermissionEvaluator implements PermissionEvaluator {
//
//    private final StudentPreferenceService preferenceService;
//    private final StudentService studentService;
//
//    @Override
//    public boolean hasPermission(Authentication auth,
//                                 Object targetDomainObject,
//                                 Object permission) {
//
//        String perm = permission.toString().toLowerCase();
//        String role = auth.getAuthorities().iterator().next().getAuthority();
//        String username = auth.getName();
//
//        // ADMIN short-circuit: always allowed
//        if (role.equals("ROLE_ADMIN")) return true;
//
//        // ----- CREATE -----
//        if ("create".equals(perm) && targetDomainObject instanceof StudentPreference pref) {
//            Long studentId = pref.getStudent().getId();
//            return isStudentOwner(username, studentId);
//        }
//
//        return false;
//    }
//
//
//    @Override
//    public boolean hasPermission(Authentication auth,
//                                 Serializable targetId,
//                                 String targetType,
//                                 Object permission) {
//
//        String perm = permission.toString().toLowerCase();
//        String role = auth.getAuthorities().iterator().next().getAuthority();
//        String username = auth.getName();
//
//        // ADMIN short-circuit
//        if (role.equals("ROLE_ADMIN")) return true;
//
//        // ----- UPDATE or DELETE ONE -----
//        if (targetType.equals("StudentPreference")) {
//
//            StudentPreference pref = preferenceService
//                    .getPreferenceById((Long) targetId)
//                    .orElse(null);
//
//            if (pref == null) return false;
//
//            return isStudentOwner(username, pref.getStudent().getId());
//        }
//
//        // ----- DELETE ALL preferences for student -----
//        if (targetType.equals("Student")
//                && "deleteallpreferences".equals(perm)) {
//
//            Long studentId = (Long) targetId;
//            return isStudentOwner(username, studentId);
//        }
//
//        return false;
//    }
//
//
//    private boolean isStudentOwner(String username, Long studentId) {
//        return studentService.getStudentByUserUsername(username)
//                .map(student -> student.getId().equals(studentId))
//                .orElse(false);
//    }
//}
