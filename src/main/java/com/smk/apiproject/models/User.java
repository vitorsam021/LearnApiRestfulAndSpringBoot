package com.smk.apiproject.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = User.USER_NAME)
public class User {

    public static final String USER_NAME = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "password", length = 100, nullable = false, unique = true)
    @NotNull
    @NotEmpty
    @Size(min = 3,max = 100)
    private String username;

    @Column(name = "password", length = 60, nullable = false, unique = true)
    @NotNull
    @NotEmpty
    @Size(min = 8,max = 60)
    private String password;



}
