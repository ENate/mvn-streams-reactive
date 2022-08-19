package com.minejava.mservice.service;

import java.util.Collections;
import java.util.List;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minejava.mservice.model.Post;
import org.springframework.data.domain.Sort;
import com.minejava.mservice.repository.PostRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ICityServiceImpl implements IPostService {

    private PostRepository postRepository;
    private final ReactiveMongoTemplate reactiveMongoTemplate;


    public ICityServiceImpl(PostRepository postRepositoryList, ReactiveMongoTemplate reactiveMongoTemplate) {
        this.postRepository = postRepositoryList;
        this.reactiveMongoTemplate = reactiveMongoTemplate;


    }

    @Override
    public Flux<Post> findAll(List<Post> posts) {
        // Create posts
        // DO Auto-generated method stub
        return postRepository.findAll();
    }

    @Override
    public Mono<Post> updatePost(String postId, Post post) {
        // DO Auto-generated method stub
         return postRepository.findById(postId)
        .flatMap(dbUser -> {
            dbUser.setTitle(post.getTitle());
            dbUser.setContent(post.getContent());
            return postRepository.save(dbUser);
        });
    }

    @Override
    public Mono<Post> findById(String pById) {
        return postRepository.findById(pById);
    }

    @Override
    public Mono<Post> createPost(Post post) {

        return postRepository.save(post);
    }

    public Mono<Post> deletePost(String pId){
        return postRepository.findById(pId)
                .flatMap(existingUser -> postRepository.delete(existingUser)
                        .then(Mono.just(existingUser)));
    }

    public Flux<Post> fetchUsers(String name) {
        Query query = new Query()
                .with(Sort
                        .by(Collections.singletonList(Sort.Order.asc("number")))
                );
        query.addCriteria(Criteria
                .where("name")
                .regex(name)
        );

        return reactiveMongoTemplate
        .find(query, Post.class);
}


}
