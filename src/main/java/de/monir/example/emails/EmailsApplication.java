package de.monir.example.emails;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Email Service",
				version = "1.0.0",
				description = "This Email Service exposes a REST API to execute CRUD operations. Endpoints are " +
						"under the route \"/emails\" and the information corresponding to the email contents are " +
						"being stored in a database.",
				contact = @Contact(
						name = "Monir M. Ahammod",
						email = "monir.ahammod@gmail.com"
				)
		)
)
public class EmailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailsApplication.class, args);
	}

}
