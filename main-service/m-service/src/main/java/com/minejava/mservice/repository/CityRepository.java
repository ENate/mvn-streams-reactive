package com.minejava.mservice.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.minejava.mservice.model.City;

@Configuration
@Repository
public interface CityRepository extends ReactiveMongoRepository<City, String> {
    
}
