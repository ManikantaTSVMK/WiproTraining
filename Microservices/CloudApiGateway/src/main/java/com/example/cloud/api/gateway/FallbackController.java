package com.example.cloud.api.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/orderFallBack")
    public Mono<String> orderServiceFallBack() {
        return Mono.just("Order Service is taking too long to respond or is down. Please try again later");
    }
    @RequestMapping("/paymentFallback")
    public Mono<String> paymentServiceFallBack() {
        return Mono.just("Payment Service is taking too long to respond or is down. Please try again later");
    }
    @RequestMapping("/defaultFallback")
    public String defaultFallback() {
        return "This is a default fallback response";
    }
}

//if threshold will leead to 50% call from order to payment will get fail then the status will change from closed (all services are up )to open
//