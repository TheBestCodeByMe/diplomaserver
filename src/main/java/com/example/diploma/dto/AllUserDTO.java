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
public class AllUserDTO {
    private String login;
    private String status;

    public AllUserDTO(String login, String status) {
        this.login = login;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllUserDTO userDTO = (AllUserDTO) o;
        return Objects.equals(login, userDTO.login) && Objects.equals(status, userDTO.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, status);
    }
}
