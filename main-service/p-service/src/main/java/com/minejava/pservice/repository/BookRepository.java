package com.minejava.pservice.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.minejava.pservice.domain.Book;

import reactor.core.publisher.Flux;

@Repository
public interface BookRepository extends R2dbcRepository<Book, Long> {
    Flux<Book> findBooksByAuthorContainingIgnoreCase(String author);

}
