package com.minejava.mservice.config;

//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.mongodb.MongoDatabaseFactory;
//import org.springframework.data.mongodb.MongoTransactionManager;
//import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;

//import com.mongodb.reactivestreams.client.MongoClient;
//import com.mongodb.reactivestreams.client.MongoClients;

//@Configuration
//@EnableReactiveMongoRepositories(basePackageClasses = {MongoConfig.class})
public class MongoConfig {
    //extends AbstractReactiveMongoConfiguration {


    /* private String databaseName = "blog";
    @Value("${mongo.uri}")
    private String mongoDbUri;

    //@Value("${mongo.uri}")
    //String mongoUri;

    public @Bean MongoClient mongoClient() {
        return MongoClients.create(mongoDbUri);
    }

    @Override
    protected String getDatabaseName() {
        // DO Auto-generated method stub
        return databaseName;
    }

    //Enabled index creation and define auto transaction
    @Bean
    MongoTransactionManager transactionManager(MongoDatabaseFactory factory) {
        return new MongoTransactionManager(factory);
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }

 */

}
