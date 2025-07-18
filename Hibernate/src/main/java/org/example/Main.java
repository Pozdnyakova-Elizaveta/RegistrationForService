package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    public static void main(String[] args) throws Exception {
        /*ClientDAO clientDAO = new ClientDAO();
        Client client = Client.builder().email("email@mail.ru").login("abracadabra").password("123444444").firstName("firstname").lastName("lastname").build();
        clientDAO.create(client);
        client = clientDAO.getByLogin(client.getLogin());
        client.setPassword("123456789");
        clientDAO.update(client);
        clientDAO.delete(client.getId());
        Specialization specialization = Specialization.builder().name("Стилист").build();
        SpecializationDAO specializationDAO = new SpecializationDAO();
        specializationDAO.create(specialization);
        List<Specialization> list = specializationDAO.getAll();
        for (Specialization s: list) System.out.println(s.getName());
        System.out.println(specializationDAO.getById(1));
        System.out.println(specializationDAO.getId("Стилист"));
        specializationDAO.delete(specializationDAO.getId("Стилист"));*/
        SpecializationDAO specializationDAO = new SpecializationDAO();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        ArrayList<Specialization> specializations = new ArrayList<>();
        specializations.add(specializationDAO.getById(1));
        specializations.add(specializationDAO.getById(2));
        Employee employee = Employee.builder().email("email@mail.ru").login("abracadabra").password("123444444").firstName("firstname").lastName("lastname")
                .address("Address").specializationList(specializations).build();
        employeeDAO.create(employee);
        employee = employeeDAO.getByLogin(employee.getLogin());
        System.out.println(employee.getSpecializationList().size());
        employee.setPassword("123456789");
        specializations = new ArrayList<>();
        specializations.add(specializationDAO.getById(1));
        employee.setSpecializationList(specializations);
        employeeDAO.update(employee);
        employee = employeeDAO.getByLogin(employee.getLogin());
        System.out.println(employee.getSpecializationList().size());
        employeeDAO.delete(employee.getId());
    }
}