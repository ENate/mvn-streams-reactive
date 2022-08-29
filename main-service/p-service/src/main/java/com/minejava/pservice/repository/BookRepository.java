package com.minejava.pservice.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.minejava.pservice.domain.Book;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BookRepository extends R2dbcRepository<Book, Long> {
    Mono<Book> findById(Long id);
    Mono<Book> findByIsbn(String isbn);
    Flux<Book> findBooksByAuthorContainingIgnoreCase(String author);

}
