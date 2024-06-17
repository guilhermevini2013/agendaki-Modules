package com.agendaki.financially.repositories;

import com.agendaki.financially.models.user.PreUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PreUserRepository extends MongoRepository<PreUser, String> {

    @Query(value = "{ 'email' : ?0 }", fields = "{ 'email' : 1, 'password' : 1, 'id' : 1 }")
    Optional<UserAuth> findByEmail(@Param("email") String email);

    interface UserAuth {
        String getId();

        String getEmail();

        String getPassword();
    }
}
