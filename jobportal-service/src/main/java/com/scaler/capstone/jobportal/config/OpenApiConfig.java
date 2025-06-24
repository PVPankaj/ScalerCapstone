package com.scaler.capstone.jobportal.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Job Portal API",
                version = "v1",
                description = "REST endpoints for the Job Portal service"
        )
)
public class OpenApiConfig { /* no extra beans needed */
}