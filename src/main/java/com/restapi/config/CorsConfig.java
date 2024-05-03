package com.restapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
@Configuration
public class CorsConfig {
    // Creating a bean for configuring CORS (Cross-Origin Resource Sharing) in the application
    @Bean
    WebMvcConfigurer corsMvcConfigurer() {
        // Creating a bean for configuring CORS (Cross-Origin Resource Sharing) in the application
        return new WebMvcConfigurer() {
            @Override
            public  void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/**").allowedMethods("*");
            }
        };
    }       
}

