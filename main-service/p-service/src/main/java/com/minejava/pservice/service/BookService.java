package com.minejava.pservice.service;

import org.springframework.stereotype.Service;

import com.minejava.pservice.domain.Book;
import com.minejava.pservice.repository.BookRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Flux<Book> findAll() {
        return bookRepository.findAll();
    }
    
    // Mono returns 0 to 1
    public Mono<Book> findById(Long id)  {
        return bookRepository.findById(id);
    }

    public Mono<Book> save(Book book) {
        return bookRepository.save(
            new Book(
                book.getTitle(),
                book.getIsbn(),
                book.getAuthor(),
                book.getPrice()
            )
        ).subscribe();
    }

    public Flux<Book> findByAuthor(String author) {
        return bookRepository.findBooksByAuthorContainingIgnoreCase(author);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id).subscribe();
    }
}
