package com.brushup.microservices.apigateway.configuration;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class APIGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder routeLocatorBuilder) {
        Function<PredicateSpec, Buildable<Route>> routeFunction = p -> p.path("/get").
                filters(f -> f.addRequestHeader("MyHeader","MyURI").
                        addRequestParameter("MyParam", "MyParamValue")).uri("http://httpbin.org:80");
        return routeLocatorBuilder.routes().route(routeFunction).
                build();
    }
}
