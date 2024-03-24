package com.demo.config;

import com.demo.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebRatingClientConfig {

    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;


    @Bean
    public WebClient ratingWebClient() {
        return WebClient.builder()
                .baseUrl("http://product-rating-service")
                .filter(filterFunction)
                .build();
    }

    @Bean
    public RatingService ratingServiceClient() {
        HttpServiceProxyFactory httpServiceProxyFactory
                = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(ratingWebClient()))
                .build();
        return httpServiceProxyFactory.createClient(RatingService.class);
    }
}
