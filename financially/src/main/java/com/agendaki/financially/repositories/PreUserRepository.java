package com.agendaki.financially.repositories;

import com.agendaki.financially.models.user.PreUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PreUserRepository extends MongoRepository<PreUser, String> {

    @Query(value = "{ 'email' : ?0 }", fields = "{ 'email' : 1, 'password' : 1, 'id' : 1 }")
    Optional<PreUserAuth> findByEmail(@Param("email") String email);

    @Query(value = "{ 'id' : ?0 }", fields = "{ 'name' : 1, 'tradeName' : 1, 'tellPhone' : 1 }")
    Optional<PreUserRead> getPreUserById(@Param("id") String id);

    interface PreUserRead {
        String getName();

        String getTradeName();

        String getTellPhone();
    }

    interface PreUserAuth {
        String getId();

        String getEmail();

        String getPassword();
    }
}
