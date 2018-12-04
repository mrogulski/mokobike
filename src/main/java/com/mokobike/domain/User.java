package com.mokobike.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
    private String email;
    private String address;
    private Integer phone;

    public User(Long id, String username, String firstName, String lastName, String email, String address, Integer phone) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }
}
