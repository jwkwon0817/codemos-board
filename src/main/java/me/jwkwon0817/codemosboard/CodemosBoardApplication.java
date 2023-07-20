package me.jwkwon0817.codemosboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CodemosBoardApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CodemosBoardApplication.class, args);
	}
}
