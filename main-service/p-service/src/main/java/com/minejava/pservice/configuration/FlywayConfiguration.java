package com.minejava.pservice.configuration;

import org.springframework.beans.factory.annotation.Value;

public class FlywayConfiguration implements CommandLineRunner{
    
    @Value("${spring.r2dbc.url}")
    private String url;
    @Value("${spring.r2dbc.username}")
    private String username;
    @Value("${spring.r2dbc.password}")
    private String password;

    @Override
    public void run(String... args) {
        Flyway.configure()
                .dataSource(url, username, password)
                .load()
                .migrate();
    }
}
