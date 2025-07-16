package org.example;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private String password;
}
