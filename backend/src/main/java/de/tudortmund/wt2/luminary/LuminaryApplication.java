package de.tudortmund.wt2.luminary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableWebMvc
public class LuminaryApplication implements WebMvcConfigurer {
	public static void main(String[] args) {
		SpringApplication.run(LuminaryApplication.class, args);
	}
}
