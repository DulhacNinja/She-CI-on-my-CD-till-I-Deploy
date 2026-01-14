//package com.example.demo.config;
//
//import com.example.demo.security.CustomPermissionEvaluator;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
//import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
//
//@Configuration
//@EnableMethodSecurity
//@RequiredArgsConstructor
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
//
//    private final CustomPermissionEvaluator permissionEvaluator;
//
//    @Override
//    protected DefaultMethodSecurityExpressionHandler createExpressionHandler() {
//        DefaultMethodSecurityExpressionHandler handler =
//                new DefaultMethodSecurityExpressionHandler();
//        handler.setPermissionEvaluator(permissionEvaluator);
//        return handler;
//    }
//}