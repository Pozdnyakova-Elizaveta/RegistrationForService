package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        final StatusJdbcRepository statusJdbcRepository = new StatusJdbcRepository();
        final SpecializationJdbcRepository specializationJdbcRepository = new SpecializationJdbcRepository();
        final ClientJdbcRepository clientJdbcRepository = new ClientJdbcRepository();
        List<String> all = statusJdbcRepository.getAll();
        all.forEach(System.out::println);
        System.out.println(statusJdbcRepository.getId("Выполнено"));
        all = specializationJdbcRepository.getAll();
        all.forEach(System.out::println);
        System.out.println(specializationJdbcRepository.getId("Окрашивание волос"));
        Client client = Client.builder().firstName("Иван").lastName("Иванов").login("lzlskddjq").password("123456789").email("email@msil.ru").build();
        clientJdbcRepository.create(client);
        client = clientJdbcRepository.getByLogin("lzlskddjq");
        clientJdbcRepository.auth("lzlskdd", "123");
        clientJdbcRepository.auth("lzlskddjq", "123");
        clientJdbcRepository.auth("lzlskddjq", "123456789");
        client.setEmail("111111@mail.ru");
        client.setLogin("ivan34567");
        clientJdbcRepository.update(client);
        clientJdbcRepository.delete(client.getId());
    }
}