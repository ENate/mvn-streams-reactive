package com.minejava.pservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
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
public class Book implements Persistable<String>{

    @Id
    //private UUID id;
    @Column("id")
    private String id;


    @Column("title")
    private String title;

    @Column("isbn")
    private String isbn;

    @Column("author")
    private String author;

    @Column("price")
    private Double price;

    @Transient
private boolean newBook;

@Override
@Transient
public boolean isNew() {
    return this.newBook || id == null;
}

public Book setAsNew(){
    this.newBook = true;
    return this;
}
}
