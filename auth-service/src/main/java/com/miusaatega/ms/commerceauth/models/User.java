package com.miusaatega.ms.commerceauth.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String firstname;
    private String lastname;
    private Double gpa;
    private LocalDate dob;
}