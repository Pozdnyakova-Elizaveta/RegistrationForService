package org.example;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements BaseEntity{
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;
    private String address;
    private List<Specialization> specializationList;
    private List<Service> serviceList;
}
