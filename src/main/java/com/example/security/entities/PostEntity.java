package com.example.security.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class PostEntity {

    private String author;
    private String text;
    private long timestamp;
}

