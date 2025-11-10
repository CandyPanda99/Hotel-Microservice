package com.example.gatewayserver.filters;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Order(2)
@Component
public class ResponseTimeFilter implements GlobalFilter {

    private static final String RESPONSE_TIME_HEADER = "X-Response-Time";
    private static final String START_TIME_ATTRIBUTE = "startTime";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {
        exchange.getAttributes().put(START_TIME_ATTRIBUTE, System.currentTimeMillis());
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    Long startTime = exchange.getAttribute(START_TIME_ATTRIBUTE);
                    if (startTime != null) {
                        long endTime = System.currentTimeMillis();
                        long duration = endTime - startTime;
                        exchange.getResponse().getHeaders().add(RESPONSE_TIME_HEADER, duration + "ms");
                    }
                })
        );
    }
}
