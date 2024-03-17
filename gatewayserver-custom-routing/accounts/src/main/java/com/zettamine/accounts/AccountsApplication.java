package com.zettamine.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.zettamine.accounts.dto.AccountsContactInfoDto;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@EnableFeignClients
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice REST API Documentation",
				description = "ZettaBank Accounts microservice REST API Documentation",
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
				description =  "ZettaBank Accounts microservice REST API Documentation",
				url =  //"https://www.zettamine.com/swagger-ui.html"
				 "http://localhost:8080/swagger-ui/index.html"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
