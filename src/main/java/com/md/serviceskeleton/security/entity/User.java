package com.md.serviceskeleton.security.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class User {

    private String login;

    private String passwoer;

    private List<String> roles;
}
