package com.bankaya.apigateway.config;

import com.bankaya.apigateway.event.IncomingRequest;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class RequestFilter implements GlobalFilter {
    private final ReactiveRedisOperations<String, IncomingRequest> requestRedisOps;

    RequestFilter(ReactiveRedisOperations<String, IncomingRequest> requestRedisOps) {
        this.requestRedisOps = requestRedisOps;
    }
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        IncomingRequest incomingRequest = new IncomingRequest();
        //incomingRequest.setId();
        incomingRequest.setOriginHost(exchange.getRequest().getRemoteAddress().getHostString());
        //incomingRequest.setDate();
        //incomingRequest.setMethod();
        requestRedisOps.opsForList().rightPush("requests", incomingRequest).subscribe();
        System.out.println(exchange.getRequest().getPath());
        System.out.println(exchange.getRequest().getId());
        System.out.println(exchange.getAttributes());
        System.out.println(exchange.getApplicationContext().getApplicationName());
        return chain.filter(exchange);
    }
}
