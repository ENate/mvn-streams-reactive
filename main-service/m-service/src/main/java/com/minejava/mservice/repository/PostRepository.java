package com.minejava.mservice.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.minejava.mservice.model.Post;

@Repository
public interface PostRepository extends ReactiveMongoRepository<Post, String> {

}
