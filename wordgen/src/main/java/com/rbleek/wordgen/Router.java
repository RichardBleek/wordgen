package com.rbleek.wordgen;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class Router {

    @Bean
    public RouterFunction<ServerResponse> route(WordService service) {
        return RouterFunctions
                .route(RequestPredicates.GET("/words"),
                        serverRequest -> ServerResponse.ok().body(service.all(), Word.class))
                .andRoute(RequestPredicates.GET("/words/one"),
                        serverRequest -> ServerResponse.ok().body(service.one(), Word.class))
                .andRoute(RequestPredicates.GET("/words/generate"),
                        serverRequest -> ServerResponse.ok()
                                .contentType(MediaType.TEXT_EVENT_STREAM)
                                .body(service.stream(), Word.class));
    }
}
