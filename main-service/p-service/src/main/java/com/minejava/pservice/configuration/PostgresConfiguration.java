package com.minejava.pservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableR2dbcRepositories
public class PostgresConfiguration {
    
    @Value("${spring.r2dbc.url}")
    private String url;
    @Value("${spring.r2dbc.username}")
    private String username;
    @Value("${spring.r2dbc.password}")
    private String password;

    @Bean
    @Override
    public ConnectionFactory getConnectionFactory()connectionFactory() {
        return new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .host(JdbcParser.getJdbcHost(url))
                        .port(JdbcParser.getJdbcPort(url))
                        .username(username)
                        .password(password)
                        .database(JdbcParser.getDbName(url))
                        .build());
    }

    @Bean
    ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }
}
