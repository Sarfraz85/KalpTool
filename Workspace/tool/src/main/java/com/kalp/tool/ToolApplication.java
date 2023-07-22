package com.kalp.tool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kalp.tool.model.User;
import com.kalp.tool.util.jwtToken;
import com.kalp.tool.util.jwtutil;

@SpringBootApplication
@EnableJpaRepositories
public class ToolApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToolApplication.class, args);
	}
	@Bean
	public jwtToken getToken() {
		return new jwtToken();
	}

	@Bean
	public User getUser() {
		return new User();
	}

	@Bean
	public jwtutil getUtil() {
		return new jwtutil();
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("*").allowedOrigins("*");
			}
		};
	}
}
