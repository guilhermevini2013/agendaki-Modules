package com.agendaki.financially.repositories;

import com.agendaki.financially.models.user.PreUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface PreUserRepository extends MongoRepository<PreUser, String> {
    @Query("{ 'email' : ?0 }")
    Optional<PreUser> findByEmail(String email);
}
