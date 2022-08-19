package com.minejava.mservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.minejava.mservice.model.Post;
import com.minejava.mservice.repository.PostRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Component
public class CodeRunnerData implements CommandLineRunner {


    private final PostRepository posts;

    public CodeRunnerData(PostRepository posts) {
        this.posts = posts;
    }

    @Override
    public void run(String... args) throws Exception {
        // DO Auto-generated method stub
        log.info("start data initialization  ...");
        this.posts
            .deleteAll()
            .thenMany(
                Flux
                    .just("Post one", "Post two")
                    .flatMap(
                        title -> this.posts.save(Post.builder().title(title).content("content of " + title).build())
                    )
            )
            .log()
            .subscribe(
                null,
                null,
                () -> log.info("done initialization...")
            );

    }

}
