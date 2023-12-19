package com.example.gatawayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class GatawayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatawayServiceApplication.class, args);
	}
	/*@Bean
	RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
		return builder.routes()
				.route(r->r.path("/customers/**").uri("http://localhost:8082/"))
				.route(r->r.path("/inventroys/**").uri("http://localhost:8080/"))
				.build();
	}*/

	@Bean
	DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp){
		return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
	}
	@Bean
	RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
		return builder.routes()
				.route(r->r.path("/customers/**").uri("lb://customer-service"))
				.route(r->r.path("/inventories/**").uri("lb://inventory-service"))
				.route(r->r.path("/suppliers/**").uri("lb://supplier-service"))
				.route(r->r.path("/products/**").uri("lb://product-service"))
				.build();
	}

}
