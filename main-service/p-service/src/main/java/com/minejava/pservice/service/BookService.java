package com.minejava.pservice.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minejava.pservice.domain.Book;
import com.minejava.pservice.repository.BookRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


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
    public Mono<Book> findById(String id)  {
        return bookRepository.findById(id);
    }
    @Override
    public Mono<Book> createBook(Book book) {
        return bookRepository.save(book.setAsNew());
    }



    public Flux<Book> findByAuthor(String author) {
        return bookRepository.findBooksByAuthorContainingIgnoreCase(author);
    }

    public Mono<Book> deleteBookById(String userId){
        return bookRepository.findById(userId)
                .flatMap(existingUser -> bookRepository.delete(existingUser)
                        .then(Mono.just(existingUser)));
    }

    @Override
    public Mono<Book> updateBook(String bId, Book book) {
        // Implemented Auto-generated method stub
        return bookRepository.findById(bId)
        .flatMap(dUser -> {
            dUser.setTitle(book.getTitle());
            dUser.setPrice(book.getPrice());
            return bookRepository.save(book.setAsNew());
        });
    }

    @Override
    public Mono<Book> deleteBook(String pId) {
        // Implement delete operation
        return bookRepository.findById(pId)
               .flatMap(eUser -> bookRepository.delete(eUser)
               .then(Mono.just(eUser)));
    }
}
