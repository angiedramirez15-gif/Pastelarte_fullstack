package com.pastelarte.pastelarte_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path rutaUploads = Paths.get("uploads").toAbsolutePath();
        String uriPath = rutaUploads.toUri().toString();

        // Garantiza que la URI termine con '/' para que Spring busque subcarpetas
        if (!uriPath.endsWith("/")) {
            uriPath += "/";
        }

        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(uriPath);
    }
}