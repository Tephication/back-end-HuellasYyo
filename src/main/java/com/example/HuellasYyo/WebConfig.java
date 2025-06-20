package com.example.HuellasYyo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //.allowedOrigins("https://frontclientes.netlify.app/")
                .allowedOrigins("http://127.0.0.1:5502","https://main.d3si1ba9lf7ht9.amplifyapp.com/")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS" , "PATCH")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}