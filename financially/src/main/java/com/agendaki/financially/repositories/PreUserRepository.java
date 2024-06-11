package com.agendaki.financially.repositories;

import com.agendaki.financially.models.user.PreUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreUserRepository extends MongoRepository<PreUser, String> {
    PreUser findByEmail(String email);
}
