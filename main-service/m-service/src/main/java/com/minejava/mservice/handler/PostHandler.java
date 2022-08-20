package com.minejava.mservice.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.minejava.mservice.model.Post;
import com.minejava.mservice.service.IPostService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PostHandler {

    private final IPostService postService;

    public Mono<ServerResponse> getAllPosts(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(postService.getAllPosts(), Post.class);
    }

    public Mono<ServerResponse> getPostById(ServerRequest request) {
        return postService
                .findById(request.pathVariable("postId"))
                .flatMap(user -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(user, Post.class)
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<Post> user = request.bodyToMono(Post.class);

        return user
                .flatMap(u -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(postService.createPost(u), Post.class)
                );
    }

    public Mono<ServerResponse> updatePostById(ServerRequest request) {
        String id = request.pathVariable("userId");
        Mono<Post> updatedUser = request.bodyToMono(Post.class);

        return updatedUser
                .flatMap(u -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(postService.updatePost(id, u), Post.class)
                );
    }

    public Mono<ServerResponse> deletePostById(ServerRequest request){
        return postService.deletePost(request.pathVariable("userId"))
                .flatMap(u -> ServerResponse.ok().body(u, Post.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}

