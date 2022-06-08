package com.minejava.mservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.minejava.mservice.model.City;
import com.minejava.mservice.repository.CityRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ICityServiceImpl implements ICityService{

    @Autowired
    private CityRepository cityRepository;

    @Override
    public Mono<City> insert(City city) {
        // DO Auto-generated method stub
        City c = new City("ER453", "Maskerder trui", 4567);
        return cityRepository.save(c);
    }

    @Override
    public Flux<City> saveAll(List<City> cities) {
        // DO Auto-generated method stub
        return null;
    }

    @Override
    public Mono<City> findById(String id) {
        // DO Auto-generated method stub
        return null;
    }

    @Override
    public Flux<City> findAll() {
        // DO Auto-generated method stub
        return null;
    }

    @Override
    public Mono<Void> deleteAll() {
        // DO Auto-generated method stub
        return null;
    }
    
}
