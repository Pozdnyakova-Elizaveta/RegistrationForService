package org.example;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        final StatusJdbcRepository statusJdbcRepository = new StatusJdbcRepository();
        final SpecializationJdbcRepository specializationJdbcRepository = new SpecializationJdbcRepository();
        final ClientJdbcRepository clientJdbcRepository = new ClientJdbcRepository();
        final EmployeeJdbcRepository employeeJdbcRepository = new EmployeeJdbcRepository();
        final EmployeeSpecializationJdbcRepository employeeSpecializationJdbcRepository = new EmployeeSpecializationJdbcRepository();
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
        Employee employee = Employee.builder().firstName("Василиса").lastName("Сидорова")
                .email("vas@mail.ru").password("457425688").login("vasss1254").address("ул Балтийская 50").build();
        employeeJdbcRepository.create(employee);
        employee = employeeJdbcRepository.getByLogin("vasss1254");
        employeeJdbcRepository.auth("lzlskdd", "123");
        employeeJdbcRepository.auth("vasss1254", "123");
        employeeJdbcRepository.auth("vasss1254", "457425688");
        employee.setPassword("gksmgklmsflkmhl");
        employeeJdbcRepository.update(employee);
        employeeSpecializationJdbcRepository.addMultipleSpecializations(employee.getId(), 1, 2);
        System.out.println(employeeSpecializationJdbcRepository.getEmployeesWithSpecialization(1));
        System.out.println(employeeSpecializationJdbcRepository.getSpecializationsEmployee(employee.getId()));
        employeeSpecializationJdbcRepository.deleteSpecialization(employee.getId(), 1);
        employeeJdbcRepository.delete(employee.getId());
    }
}