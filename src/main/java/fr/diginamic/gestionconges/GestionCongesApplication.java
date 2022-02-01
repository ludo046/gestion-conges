package fr.diginamic.gestionconges;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class GestionCongesApplication {
	
    @SuppressWarnings("deprecation")
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                        .allowedHeaders("Content-Type");
            }
        };
    }

	public static void main(String[] args) {
		SpringApplication.run(GestionCongesApplication.class, args);
	}

}
