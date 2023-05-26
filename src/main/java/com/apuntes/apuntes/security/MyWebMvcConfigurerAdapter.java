package com.apuntes.apuntes.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurerAdapter implements WebMvcConfigurer {
    /**
     * Configurar recursos de acceso estático
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/uploads/**").addResourceLocations("file:C:/Users/ghuerta/Documents/proyecto_apuntes/back/apuntes/uploads/");
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:uploads/");
    }
}
