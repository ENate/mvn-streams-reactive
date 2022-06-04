package com.minejava.pservice.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
@Table(name = "Book")
public class Book {
    
    
    private Long id;
    private String title;
    private String isbn;
    private String author;
    private BigDecimal price;


    public Book(String title, String isbn, String author, BigDecimal price) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.price = price;
    }

}
