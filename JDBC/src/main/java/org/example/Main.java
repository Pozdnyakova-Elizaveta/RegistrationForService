package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        final StatusJdbcRepository statusJdbcRepository = new StatusJdbcRepository();
        final SpecializationJdbcRepository specializationJdbcRepository = new SpecializationJdbcRepository();
        List<String> all = statusJdbcRepository.getAll();
        all.forEach(System.out::println);
        System.out.println(statusJdbcRepository.getId("Выполнено"));
        all = specializationJdbcRepository.getAll();
        all.forEach(System.out::println);
        System.out.println(specializationJdbcRepository.getId("Окрашивание волос"));
    }
}