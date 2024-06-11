package com.agendaki.financially.configurations.mongoDB;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Query;

@Configuration
public class MongoDBConfig implements ApplicationRunner {
    private final MongoTemplate mongoTemplate;

    public MongoDBConfig(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(ApplicationArguments args) {
        mongoTemplate.remove(new Query(), "pre-user");
        mongoTemplate.indexOps("pre-user").ensureIndex(new Index("email", Sort.Direction.ASC).unique());
    }
}
