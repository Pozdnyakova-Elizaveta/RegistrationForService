package org.example;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private String password;
}
