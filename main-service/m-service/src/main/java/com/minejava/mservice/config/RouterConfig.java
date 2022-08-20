package com.minejava.mservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.minejava.mservice.handler.PostHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {


    @Bean
    RouterFunction<ServerResponse> routes(PostHandler handler) {
        return route(GET("/handler/posts/allposts").and(accept(MediaType.APPLICATION_JSON)), handler::getAllPosts)
                .andRoute(GET("/handler/posts/{posterId}").and(contentType(MediaType.APPLICATION_JSON)), handler::getPostById)
                .andRoute(POST("/handler/posts/create").and(accept(MediaType.APPLICATION_JSON)), handler::create)
                .andRoute(PUT("/handler/posts/{postId}").and(contentType(MediaType.APPLICATION_JSON)), handler::updatePostById)
                .andRoute(DELETE("/handler/posts/{postId}").and(accept(MediaType.APPLICATION_JSON)), handler::deletePostById);
    }
}
