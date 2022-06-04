package com.minejava.mservice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.minejava.mservice.model.City;
import com.minejava.mservice.service.ICityService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CodeRunnerData implements CommandLineRunner{
    private static final Logger logger = LoggerFactory.getLogger(CodeRunnerData.class);

    @Autowired
    private ICityService cityService;
    @Override
    public void run(String... args) throws Exception {
        // DO Auto-generated method stub
        logger.info("Creating cities");

        var cities = List.of(new City("BRDG23", "Bratislava", 432000),
                new City("GHD65T", "Budapest", 1759000),
                new City("NBHG21", "Prague", 1280000),
                new City("HJKI89","Warsaw", 1748000));

        Mono<Void> one = cityService.deleteAll();

        Flux<City> two = cityService.saveAll(cities);
        Flux<City> three = cityService.findAll();
        three.subscribe(city -> logger.info("{}", city));

        Mono<Void> all = Mono.when(one, two, three);
        all.block();
    }
}
