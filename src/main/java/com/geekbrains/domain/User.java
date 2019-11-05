package com.geekbrains.domain;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String nickname;

    public User() {
    }

    public User(String firstName, String lastName, String nickname, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.password = password;
    }
}
