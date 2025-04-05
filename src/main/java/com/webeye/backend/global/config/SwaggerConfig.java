package com.webeye.backend.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(title = "WebEye API 명세서",
                description = "WebEye API 명세서",
                version = "v1"
        ),
        servers = @Server(url = "/api", description = "Default Server URL")
)

public class SwaggerConfig {
}
