package com.example.gatewayserver;

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
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(
					p -> p
						.path("/gateway/hotel/**")
						.filters(f -> f.rewritePath("/gateway/hotel/(?<segment>.*)", "/${segment}"))
						.uri("lb://HOTEL")
				)
				.route(
					p -> p
						.path("/gateway/reservation/**")
						.filters(f -> f.rewritePath("/gateway/reservation/(?<segment>.*)", "/${segment}"))
						.uri("lb://RESERVATION")
				)
				.route(
				p -> p
						.path("/gateway/user/**")
						.filters(f -> f.rewritePath("/gateway/user/(?<segment>.*)", "/${segment}"))
						.uri("lb://USER")
				)
				.build();
	}
}
