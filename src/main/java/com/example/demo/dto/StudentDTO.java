package com.example.demo.dto;

import com.example.demo.entity.Role;

public record StudentDTO(
        Long id,
        String username,
        String password,
        String email,
        String name,
        String code,
        Integer year,
        Role role
) {}