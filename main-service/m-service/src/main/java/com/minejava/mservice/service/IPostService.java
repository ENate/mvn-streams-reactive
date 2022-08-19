package com.minejava.mservice.service;

import java.util.List;
import com.minejava.mservice.model.Post;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPostService {
    Flux<Post> findAll(List<Post> posts);
    Mono<Post> createPost(Post post);

    Mono<Post> updatePost(String userId,  Post post);
    Mono<Post> findById(String postById);

    Mono<Post> deletePost(String pId);


}
