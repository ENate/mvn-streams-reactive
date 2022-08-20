package com.minejava.pservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


import com.minejava.pservice.handler.BookHandler;

@Configuration
public class RouterConfig {
    @Bean
    RouterFunction<ServerResponse> routes(BookHandler handler) {
        return route(GET("/handler/books").and(accept(MediaType.APPLICATION_JSON)), handler::getAllBooks)
                .andRoute(GET("/handler/books/{id}").and(contentType(MediaType.APPLICATION_JSON)), handler::getBookById)
                .andRoute(POST("/handler/books/create").and(accept(MediaType.APPLICATION_JSON)), handler::createBookItem)
                //.andRoute(PUT("/handler/users/{userId}").and(contentType(MediaType.APPLICATION_JSON)), handler::updateUserById)
                .andRoute(DELETE("/handler/books/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::deleteBookById);
    }
}
