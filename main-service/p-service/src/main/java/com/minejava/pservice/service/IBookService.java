package com.minejava.pservice.service;

import com.minejava.pservice.domain.Book;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBookService {
    Flux<Book> getAllBooks();
    Mono<Book> createBook(Book book);
    Mono<Book> findByIsbn(String isbn);

    Mono<Book> updateBook(String userId,  Book book);
    Mono<Book> findBookById(Long id);

    Mono<Book> deleteBook(String pId);
}
