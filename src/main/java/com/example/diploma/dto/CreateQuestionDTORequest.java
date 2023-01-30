package com.example.diploma.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateQuestionDTORequest implements Serializable {
    private String question;
}