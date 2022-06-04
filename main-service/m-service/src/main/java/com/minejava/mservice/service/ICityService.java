package com.minejava.mservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.minejava.mservice.model.City;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface ICityService {
    Mono<City> insert(City city);
    Flux<City> saveAll(List<City> cities);
    Mono<City> findById(String id);
    Flux<City> findAll();
    Mono<Void> deleteAll();

    
}
