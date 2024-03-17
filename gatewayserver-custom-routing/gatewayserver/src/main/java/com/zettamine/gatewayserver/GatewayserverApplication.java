package com.zettamine.gatewayserver;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
		
	}
	
	@Bean
	public RouteLocator zettaBankRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
		
		return routeLocatorBuilder.routes().route(r -> r.path("/zettabank/accounts/**")
				                                        .filters(f -> f.rewritePath("/zettabank/accounts/(?<segment>.*)", "/${segment}")
				                                        		.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
				                                        .uri("lb://ACCOUNTS"))
				                  
				                           .route(r -> r.path("/zettabank/loans/**")
				                        		         .filters(f -> f.rewritePath("/zettabank/loans/(?<segment>.*)","/${segment}" )
				                        		        		 .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
				                        		          .uri("lb://LOANS"))
				                           
				                           .route(r -> r.path("/zettabank/cards/**")
				                        		         .filters(f -> f.rewritePath("/zettabank/cards/(?<segment>.*)", "/${segment}")
				                        		        		 .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
				                        		         .uri("lb://CARDS"))
				                           .build();
		
	}

}