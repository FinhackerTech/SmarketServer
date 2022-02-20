package dev.finhacker.smarket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                String vueAddr = "*";
                registry.addMapping("/login/api/**").allowedOriginPatterns(vueAddr).allowCredentials(true).allowedMethods("GET", "POST", "PUT", "DELETE");
                registry.addMapping("/enterprise/api/**").allowedOriginPatterns(vueAddr).allowCredentials(true).allowedMethods("GET", "POST", "PUT", "DELETE");
                registry.addMapping("/myfavourite/api/**").allowedOriginPatterns(vueAddr).allowCredentials(true).allowedMethods("GET", "POST", "PUT", "DELETE");
                registry.addMapping("/setting/api/**").allowedOriginPatterns(vueAddr).allowCredentials(true).allowedMethods("GET", "POST", "PUT", "DELETE");

            }
        };
    }

}
