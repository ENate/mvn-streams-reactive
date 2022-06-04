package com.minejava.pservice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.minejava.pservice.domain.Book;

import reactor.core.publisher.Flux;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book, Long> {
    Flux<Book> findBooksByAuthorContainingIgnoreCase(String author);

}
