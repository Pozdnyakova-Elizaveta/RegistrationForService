package org.example;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;
    private String address;
}
