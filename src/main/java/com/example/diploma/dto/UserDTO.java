package com.example.diploma.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {
    private String name;
    private String lastname;
    private String patronymic;
    private String email;
    private String login;
    private String password;
    private String role;
    private String status;
    private String link;

    public UserDTO(String name, String lastname, String patronymic, String email, String login, String password, String role, String status, String link) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(name, userDTO.name) && Objects.equals(lastname, userDTO.lastname) && Objects.equals(patronymic, userDTO.patronymic) && Objects.equals(email, userDTO.email) && Objects.equals(login, userDTO.login) && Objects.equals(password, userDTO.password) && Objects.equals(role, userDTO.role) && Objects.equals(status, userDTO.status) && Objects.equals(link, userDTO.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastname, patronymic, email, login, password, role, status, link);
    }
}
