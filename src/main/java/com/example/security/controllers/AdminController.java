package com.example.security.controllers;

import com.example.security.entities.AppUserEntity;
import com.example.security.entities.MinimalUser;
import com.example.security.repositories.AppUserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private AppUserRepository appUserRepository;

    public AdminController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @GetMapping(value = "/users")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public List<MinimalUser> getAllUsers() {
        List<AppUserEntity> detailedEntities = appUserRepository.findAll();
        List<MinimalUser> minimalUsers = new ArrayList<>();
        for(AppUserEntity appUserEntity : detailedEntities) {
            minimalUsers.add(new MinimalUser(appUserEntity));
        }
        return minimalUsers;
    }
}
