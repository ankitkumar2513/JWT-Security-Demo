package com.example.security.controllers;

import com.example.security.entities.AppUserEntity;
import com.example.security.repositories.AppUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collections;

@RestController
public class HomeController {

    private AppUserRepository appUserRepository;

    public HomeController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @GetMapping(value = "/hello")
    public ResponseEntity<String> sayHello() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AppUserEntity> register(@RequestBody AppUserEntity appUserEntity) {
        if(appUserRepository.findFirstByUsername(appUserEntity.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }
        appUserEntity.setRoles(Collections.singletonList("USER"));
        appUserRepository.save(appUserEntity);
        return new ResponseEntity<>(appUserEntity, HttpStatus.CREATED);
    }

    @GetMapping(value = "/user")
    public Principal user(Principal principal) {
        return principal;
    }
}
