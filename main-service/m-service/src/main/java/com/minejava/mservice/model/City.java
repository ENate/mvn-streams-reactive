package com.minejava.mservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.ToString;


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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }



}
