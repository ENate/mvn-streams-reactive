package com.minejava.mservice.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.minejava.mservice.model.City;

public interface CityRepository extends ReactiveMongoRepository<City, String> {
    
}
