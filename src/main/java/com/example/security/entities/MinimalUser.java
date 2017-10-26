package com.example.security.entities;


import lombok.Getter;

import java.util.List;

@Getter
public class MinimalUser {

    private String id;
    private String username;
    private String name;
    private List<String> roles;

    public MinimalUser(final AppUserEntity appUserEntity) {
        id = appUserEntity.getId();
        username = appUserEntity.getUsername();
        name = appUserEntity.getName();
        roles = appUserEntity.getRoles();
    }
}
