package com.minejava.pservice.controller;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import lombok.RequiredArgsConstructor;
import com.minejava.pservice.domain.Book;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.minejava.pservice.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookRestController {

    @Autowired
    private final BookService bookService;

    //@GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @GetMapping("/all")
    public Flux<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Book> create(@RequestBody Book book){
        return bookService.createBook(book);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Book>> getBookById(@PathVariable String id){
        Mono<Book> booker = bookService.findById(id);
        return booker.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    // @GetMapping("/{author}")
    // public Flux<Book> findByAuthor(@RequestParam String name) {
    //     return bookService.findByAuthor(name);
    // }

    // @GetMapping("/search")
    // public Flux<Book> searchUsers(@RequestParam("title") String title) {
    //     return bookService.fetchUsers(title);
    // }

    @PutMapping("/{bookId}")
    public Mono<ResponseEntity<Book>> updateBookById(@PathVariable String bookId, @RequestBody Book book){
        return bookService.updateBook(bookId, book)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{bookId}")
    public Mono<ResponseEntity<Void>> deleteUserById(@PathVariable String bookId){
        return bookService.deleteBookById(bookId)
                .map( r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Book> streamAllUsers() {
        return bookService
                .getAllBooks()
                .flatMap(user -> Flux
                        .zip(Flux.interval(Duration.ofSeconds(2)),
                                Flux.fromStream(Stream.generate(() -> user))
                        )
                        .map(Tuple2::getT2)
                );
    }

}
