package com.example.diploma.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SignUpRequest {
    private String name;
    private String lastname;
    private String patronymic;
    private String email;
    private String login;
    private String password;
    private Set<String> role;
    private String status;
    private String link;

    public SignUpRequest(String name, String lastname, String patronymic, String email, String login, String password, Set<String> role, String status, String link) {
        this.name = name;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
        this.status = status;
        this.link = link;
    }
}
