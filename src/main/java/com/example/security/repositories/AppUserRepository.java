package com.example.security.repositories;

import com.example.security.entities.AppUserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends MongoRepository<AppUserEntity, String> {

    AppUserEntity findFirstByUsername(String username);
}
