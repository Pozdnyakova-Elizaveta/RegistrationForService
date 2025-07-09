package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeJdbcRepository {
    public void delete(int id){
        String query = "DELETE from employee where id_employee = ?";
        try(Connection connection = AppConstant.ds.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, id);
            int row = statement.executeUpdate();
            if (row>0) System.out.println("Запись о сотруднике удалена");
            else System.out.println("Не удалось удалить запись");
        } catch (SQLException e){
            System.out.println("Ошибка SQL-запроса");
        }
    }
    public void create(Employee employee){
        String query = "INSERT INTO employee (first_name, last_name, login, password, email, address) values (?, ?, ?, ?, ?, ?)";
        try(Connection connection = AppConstant.ds.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getLogin());
            statement.setString(4, employee.getPassword());
            statement.setString(5, employee.getEmail());
            statement.setString(6, employee.getAddress());
            int row = statement.executeUpdate();
            if (row>0) System.out.println("Запись о сотруднике создана");
            else System.out.println("Не удалось создать запись");
        } catch (SQLException e){
            System.out.println("Ошибка SQL-запроса");
        }

    }
    public void update(Employee employee){
        String query = "UPDATE employee set first_name = ?, last_name = ?, login = ?, password = ?, email = ?, address = ? " +
                "where id_employee = ?";
        try(Connection connection = AppConstant.ds.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getLogin());
            statement.setString(4, employee.getPassword());
            statement.setString(5, employee.getEmail());
            statement.setString(6, employee.getAddress());
            statement.setInt(7, employee.getId());
            int row = statement.executeUpdate();
            if (row>0) System.out.println("Запись сотрудника успешно изменена");
            else System.out.println("Не удалось изменить запись сотрудника");
        }catch (SQLException e){
            System.out.println("Ошибка SQL-запроса");
        }
    }
    public void auth(String login, String password){
        Employee employee = getByLogin(login);
        if (employee==null) System.out.println("Логин введен неверно");
        else if (employee.getPassword().equals(password)) System.out.println("Вход выполнен успешно");
        else System.out.println("Пароль введен неверно");
    }
    public Employee getByLogin(String login){
        Employee employee = null;
        String query = "SELECT * from employee where login = ?";
        try(Connection connection = AppConstant.ds.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) employee=Employee.builder().id(resultSet.getInt("id_employee"))
                    .firstName(resultSet.getString("first_name")).lastName(resultSet.getString("last_name"))
                    .email(resultSet.getString("email")).login(resultSet.getString("login"))
                    .password(resultSet.getString("password"))
                    .address(resultSet.getString("address")).build();
        } catch (SQLException e){
            System.out.println("Ошибка SQL-запроса");
        }
        return employee;
    }
}
