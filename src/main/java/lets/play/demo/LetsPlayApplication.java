package lets.play.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;

@SpringBootApplication()
public class LetsPlayApplication {
	public static void main(String[] args) {
		SpringApplication.run(LetsPlayApplication.class, args);
	}
}
