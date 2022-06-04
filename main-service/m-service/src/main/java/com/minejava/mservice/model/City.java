package com.minejava.mservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Data
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Document(value = "cities")
public class City {
    
    @Id
    private String id;

    private String name;
    private int population;

    public City() {
        // empty constructor
    }

    public City(String id, String name, int population) {
        this.id = id;
        this.name = name;
        this.population = population;
    }



}
