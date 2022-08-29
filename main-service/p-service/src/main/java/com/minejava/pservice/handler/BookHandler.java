package com.minejava.pservice.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.http.HttpStatus;

import com.minejava.pservice.domain.Book;
import com.minejava.pservice.service.BookService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BookHandler {

    private final BookService nBookService;

    // to use constructor init via required args constructor

    public Mono<ServerResponse>  getAllBooks(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(nBookService.getAllBooks(), Book.class);
    }

    public Mono<ServerResponse> createBookItem(ServerRequest request) {
        Mono<Book> book = request.bodyToMono(Book.class);

        return book
                .flatMap(u -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(nBookService.createBook(u), Book.class)
                );
    }


    public Mono<ServerResponse> updateBookById(ServerRequest request) {
        String id = request.pathVariable("bookId");
        Mono<Book> updatedBook = request.bodyToMono(Book.class);

        return updatedBook
                .flatMap(u -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(nBookService.updateBook(id, u), Book.class)
                );
    }
    public Mono<ServerResponse> getBookById(ServerRequest request) {
        return nBookService
                .findByIsbn(request.pathVariable("id"))
                .flatMap(user -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(user, Book.class)
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> deleteBookById(ServerRequest request){
        return nBookService.deleteBook(request.pathVariable("bookId"))
                .flatMap(u -> ServerResponse.ok().body(u, Book.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

}
