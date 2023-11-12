package study.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ApiHealthBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiHealthBasicApplication.class, args);
	}

}
