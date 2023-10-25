package com.viit0r.cursospring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${cors.origins:defaultValue}")
    private String corsOrigins = "";

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        String[] originsPermitidas = corsOrigins.split(",");

        registry.addMapping("/**")
                //.allowedMethods("GET", "POST", "PUT", "DELETE"); Podemos especificar qual método queremos
                .allowedMethods("*")
                .allowedOrigins(originsPermitidas)
                .allowCredentials(true);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        //Via QueryString: http://localhost:8080/api/person/v1?mediaType=xml
        /*configurer.favorParameter(true)
                .parameterName("mediaType")
                .ignoreAcceptHeader(true)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML);
                */

        //Via HEADER: http://localhost:8080/api/person/v1
        //Parametro Accept no header da requisição
        configurer.favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML);
    }
}
