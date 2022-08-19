package com.minejava.pservice.domain;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    private UUID id;

    @Column("title")
    private String title;

    @Column("isbn")
    private String isbn;

    @Column("author")
    private String author;

    @Column("price")
    private BigDecimal price;


    public Book(String title, String isbn, String author, BigDecimal price) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.price = price;
    }

}
