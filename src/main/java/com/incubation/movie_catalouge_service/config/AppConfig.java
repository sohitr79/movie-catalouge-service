package com.incubation.movie_catalouge_service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Bean
    @LoadBalanced // with loadBalenced annotation it will use Eureka server
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public WebClient.Builder getWebClientBuilder(){
        return WebClient.builder();
    }
}
