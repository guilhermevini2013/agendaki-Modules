package com.agendaki.financially.configurations.mongoDB;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

@Configuration
public class MongoDBConfig implements ApplicationRunner {
    private final MongoTemplate mongoTemplate;

    public MongoDBConfig(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        mongoTemplate.remove(new Query(), "pre-user");
    }
}
