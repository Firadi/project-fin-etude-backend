package com.project_technique.project_technique.dto;



public record AuthResponse(
        String jwt,
        String role
) { }
