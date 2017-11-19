package com.example.security.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Setter
@Getter
@Document(collection ="Problem2")
@CompoundIndexes({
        @CompoundIndex(name = "problem_post", def = "{ 'problem.posts': 1 }", unique = true)
})
public class ProblemEntity {

    @Id
    private String id;
    private ArrayList<PostEntity> posts;
    private ArrayList<String> tags;

    //generated and added by server
    private long timestamp;
    private String authorUsername;

    //must be provided by the request
    private String title;
    private String difficulty;
    private String statement;
    private String input;
    private String output;
    private String inputFormat;
    private String outputFormat;
    private String sampleInput;
    private String sampleOutput;
    private String explanation;
}



