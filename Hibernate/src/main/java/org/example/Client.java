package org.example;

import lombok.*;

import java.util.List;

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
    private List<Book> books;
}
