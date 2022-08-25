package com.winds.crud.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "User crud",
                version = "v1.0.0x",
                description = "User crud RESTful application"))
public class SwaggerConfig {
    // It is able to add groupApi
}
