package com.example.myretail.config;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

@Configuration
public class MongoDbConfiguration {

    public @Bean MongoDbFactory getMongoDbFactory() throws UnknownHostException {

        return new SimpleMongoDbFactory(new MongoClient("localhost",27017),"mongotest");

    }

    public @Bean(name = "mongoTemplate") MongoTemplate getMongoTemplate() throws UnknownHostException {

        MongoTemplate mongoTemplate = new MongoTemplate(getMongoDbFactory());

        return mongoTemplate;

    }

}
