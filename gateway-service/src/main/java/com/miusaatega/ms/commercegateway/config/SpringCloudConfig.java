package com.miusaatega.ms.commercegateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth" , r -> r.path("/api/auth/**")
//                        .filters(f -> f.filter(filter))
                        .uri("lb://AUTH-SERVICE"))
                .route("account" , r -> r.path("/api/account/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://ACCOUNT-SERVICE"))
                .build();
    }

}