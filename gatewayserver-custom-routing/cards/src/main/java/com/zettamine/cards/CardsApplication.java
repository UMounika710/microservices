package com.zettamine.cards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.zettamine.cards.dto.CardsContactInfoDto;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {CardsContactInfoDto.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Cards microservice REST API Documentation",
				description = "ZettaBank Cards microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "uppuluru Mounika",
						email = "mounika.u@zettamine.com",
						url = "https://www.zettamine.com"
				),
				license = @License(
						name = "Zetta 2.0",
						url = "https://www.zettamine.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "ZettaBank Cards microservice REST API Documentation",
				url =  //"https://www.zettamine.com/swagger-ui.html"
				 "http://localhost:9000/swagger-ui/index.html"
		)
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
