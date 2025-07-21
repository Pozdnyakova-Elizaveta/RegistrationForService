package org.example;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Specialization implements BaseEntity{
    private int id;
    private String name;
}
