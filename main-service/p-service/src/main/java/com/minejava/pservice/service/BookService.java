package com.minejava.pservice.service;
// import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minejava.pservice.domain.Book;
import com.minejava.pservice.repository.BookRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
//import org.springframework.integration.handler.advice.RequestHandlerCircuitBreakerAdvice.CircuitBreakerOpenException
@Log4j2
@Service
@Transactional
public class BookService implements IBookService{

    private final BookRepository bookRepository;

    public BookService(BookRepository bRepository) {
        this.bookRepository = bRepository;
    }

    @Override
    public Flux<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Mono returns 0 to 1
    @Override
    @Retry(name = "books")
    @CircuitBreaker(name = "books")
    public Mono<Book> findBookById(Long id)  {
        return bookRepository.findById(id);
        // .onErrorMap(RetryExceptionWrapper.class, Throwable::getCause)
        //.onErrorReturn(CircuitBreakerOpenException.class, getProductFallbackValue(id));
    }
    private Object getProductFallbackValue(Long id) {

        if (id == 14) {
            String errMsg = " Id: " + id + " not found in fallback cache!";
            log.warn(errMsg);
            throw new RuntimeException(errMsg);
        }

        return null;
    }

    @Override
    public Mono<Book> createBook(Book book) {
        return bookRepository.save(book);
    }



    public Flux<Book> findByAuthor(String author) {
        return bookRepository.findBooksByAuthorContainingIgnoreCase(author);
    }

    @Override
    public Mono<Book> updateBook(String bId, Book book) {
        // Implemented Auto-generated method stub
        return bookRepository.findByIsbn(bId)
        .flatMap(dUser -> {
            dUser.setTitle(book.getTitle());
            dUser.setPrice(book.getPrice());
            return bookRepository.save(book);
        });
    }

    @Override
    public Mono<Book> deleteBook(String isbn) {
        // Implement delete operation
        return bookRepository.findByIsbn(isbn)
            .flatMap(eUser -> bookRepository.delete(eUser)
            .then(Mono.just(eUser)));
    }

    @Override
    public Mono<Book> findByIsbn(String isbn) {
        // Done Auto-generated method stub
        return bookRepository.findByIsbn(isbn);
    }
}
