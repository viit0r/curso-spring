package com.viit0r.cursospring.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI () {
        return new OpenAPI()
                .info(new Info()
                        .title("APIs RESTFul com Java 1 e Spring boot 3")
                        .version("v1")
                        .description("Alguma descrição sobre a nossa API")
                        .termsOfService("https://www.google.com")
                        .license(
                                new License()
                                        .name("Apache 2.0")
                                        .url("https://www.google.com")
                        )
                );
    }
}
