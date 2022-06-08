package com.minejava.pservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.minejava.pservice.domain.Book;
import com.minejava.pservice.service.BookService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookRestController {

    @Autowired
    private final BookService bookService;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Book> list() {
        return bookService.findAll();
    }

    @GetMapping("/books/{id}")
    public Mono<Book> findById(Long id) {
        return bookService.findById(id);
    }


    @GetMapping("/author")
    public Flux<Book> findByAuthor(@RequestParam String name) {
        return bookService.findByAuthor(name);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
