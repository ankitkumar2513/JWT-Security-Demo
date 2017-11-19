package com.example.security.controllers;

import com.example.security.entities.AppUserEntity;
import com.example.security.entities.PostEntity;
import com.example.security.entities.ProblemEntity;
import com.example.security.repositories.ProblemRepository;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class ProblemController {

    private ProblemRepository problemRepository;
    private MongoOperations mongoOperations;

    public ProblemController(ProblemRepository problemRepository, MongoOperations mongoOperations) {
        this.problemRepository = problemRepository;
        this.mongoOperations = mongoOperations;
    }

    @GetMapping(value = "/getproblem/{title}")
    public ProblemEntity getProblem(@PathVariable(value = "title") String title) {
        return problemRepository.findFirstByTitle(title);
    }

    @PostMapping(value = "/addproblem")
    public void addProblem(Principal principal, @RequestBody ProblemEntity problemEntity) throws Exception {
        String username = ((AppUserEntity)((Authentication) principal).getPrincipal()).getUsername();
        problemEntity.setAuthorUsername(username);
        problemEntity.setTimestamp(System.currentTimeMillis());
        if(problemEntity.getTitle() == null)
            throw new Exception("Not a valid problem");
        this.problemRepository.save(problemEntity);
    }

    @PostMapping(value = "/addpost/{id}")
    public ResponseEntity<PostEntity> addPost(Principal principal, @RequestBody PostEntity entity, @PathVariable(value = "id") String id) {
        String username = ((AppUserEntity)((Authentication) principal).getPrincipal()).getUsername();
        entity.setAuthor(username);
        entity.setTimestamp(System.currentTimeMillis());
        Query query = new Query(Criteria.where("id").is(id));
        mongoOperations.findAndModify(query, new Update().addToSet("posts", entity), ProblemEntity.class);
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @GetMapping(value = "/getposts/{id}")
    public List<PostEntity> getPosts(@PathVariable(value = "id") String id) {
        return problemRepository.findById(id).get().getPosts();
    }

    @GetMapping(value = "/getpostsbytitle/{title}")
    public List<PostEntity> getPostsByTitle(@PathVariable(value = "title") String title) {
        return problemRepository.findFirstByTitle(title).getPosts();
    }
}
