package com.project_technique.project_technique.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Logement API",
                description = "API documentation for managing logements",
                version = "v1.0"
        )
)

public class SwaggerConfig {
}
