package com.example.security.repositories;

import com.example.security.entities.ProblemEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends MongoRepository<ProblemEntity,String> {

    ProblemEntity findFirstById(String Id);
    ProblemEntity findFirstByTitle(String title);
}
