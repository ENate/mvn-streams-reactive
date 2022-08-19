package com.minejava.mservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.minejava.mservice.model.Post;
import com.minejava.mservice.service.IPostServiceImpl;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final IPostServiceImpl postServiceImpl;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Mono<Post> create(@RequestBody Post post) {
        return postServiceImpl.createPost(post);
    }

    @GetMapping("/{postId}")
    public Mono<ResponseEntity<Post>> getUserById(@PathVariable String pId){
        Mono<Post> user = postServiceImpl.findById(pId);
        return user.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Flux<Post> getAllUsers(List<Post> posts){
        return postServiceImpl.findAll(posts);
    }

    @DeleteMapping("/{userId}")
    public Mono<ResponseEntity<Void>> deleteUserById(@PathVariable String posterId){
        return postServiceImpl.deletePost(posterId)
                .map( r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{userId}")
    public Mono<ResponseEntity<Post>> updateUserById(@PathVariable String postId, @RequestBody Post post){
        return postServiceImpl.updatePost(postId, post)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @GetMapping("/search")
    public Flux<Post> searchUsers(@RequestParam("title") String title) {
        return postServiceImpl.fetchUsers(title);
    }

}
