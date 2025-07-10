package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSpecializationJdbcRepository {
    private final SpecializationJdbcRepository specializationJdbcRepository = new SpecializationJdbcRepository();
    private final EmployeeJdbcRepository employeeJdbcRepository = new EmployeeJdbcRepository();
    public void addMultipleSpecializations(int idEmployee, int ... idSpecializations) throws SQLException {
        Connection connection = AppConstant.ds.getConnection();
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        for (int id : idSpecializations) {
            String query = "INSERT INTO employee_specialization (employee_id, specialization_id) values (" + idEmployee +
                    ", " + id + ")";
            statement.addBatch(query);
        }
        statement.executeBatch();
        System.out.println("Специализации сотрудника добавлены");
        connection.commit();
        statement.close();
        connection.close();
    }
    public void deleteSpecialization(int idEmployee, int idSpecialization){
        String query = "DELETE from employee_specialization where employee_id = ? and specialization_id = ?";
        try(Connection connection = AppConstant.ds.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, idEmployee);
            statement.setInt(2, idSpecialization);
            int row = statement.executeUpdate();
            if (row>0) System.out.println("Специализация работника удалена");
            else System.out.println("Не удалось удалить специализацию");
        }catch (SQLException e){
            System.out.println("Ошибка SQL-запроса");
        }
    }
    public List<String> getSpecializationsEmployee(int idEmployee){
        List<String> list = new ArrayList<>();
        String query = "Select specialization_id from employee_specialization where employee_id = ?";
        try(Connection connection = AppConstant.ds.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, idEmployee);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) list.add(specializationJdbcRepository.getName(resultSet.getInt("specialization_id")));
        } catch (SQLException e){
            System.out.println("Ошибка SQL-запроса");
        }
        return list;
    }
    public List<Employee> getEmployeesWithSpecialization(int idSpecialization){
        List<Employee> list = new ArrayList<>();
        String query = "Select employee_id from employee_specialization where specialization_id = ?";
        try(Connection connection = AppConstant.ds.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, idSpecialization);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) list.add(employeeJdbcRepository.getById(resultSet.getInt("employee_id")));
        }catch (SQLException e){
            System.out.println("Ошибка SQL-запроса");
        }
        return list;
    }
}
