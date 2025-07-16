package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    public static void main(String[] args) throws Exception {
        ClientService clientService = new ClientService();
        Client client = Client.builder().email("email@mail.ru").login("abracadabra").password("123444444").firstName("firstname").lastName("lastname").build();
        clientService.create(client);
        client = clientService.getByLogin(client.getLogin());
        clientService.auth(client.getLogin(), client.getPassword());
        client.setPassword("123456789");
        clientService.update(client);
        clientService.auth(client.getLogin(), "123444444");
        clientService.delete(client.getId());
        Specialization specialization = Specialization.builder().name("Стилист").build();
        SpecializationService specializationService = new SpecializationService();
        specializationService.create(specialization);
        List<Specialization> list = specializationService.getAll();
        for (Specialization s: list) System.out.println(s.getName());
        System.out.println(specializationService.getName(1));
        System.out.println(specializationService.getId("Стилист"));
        specializationService.delete(specializationService.getId("Стилист"));
    }
}