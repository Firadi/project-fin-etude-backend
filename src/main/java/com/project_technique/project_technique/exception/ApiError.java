package com.project_technique.project_technique.exception;


import java.time.LocalDateTime;

public record ApiError(
        String path,
        String message,
        int statusCode,
        LocalDateTime date
) { }
