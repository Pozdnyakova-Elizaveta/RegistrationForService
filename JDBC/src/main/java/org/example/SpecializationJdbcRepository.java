package org.example;

import java.sql.*;
import java.util.ArrayList;

public class SpecializationJdbcRepository {
    public ArrayList<String> getAll(){
        ArrayList<String> specializations = new ArrayList<>();
        String query = "SELECT * FROM specialization";
        try(Connection connection = AppConstant.ds.getConnection();
        Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                specializations.add(resultSet.getString("name_specialization"));
            }
        }catch(SQLException e){
            System.out.println("Ошибка SQL-запроса");
        }
        return specializations;
    }
    public int getId(String name){
        String query = "SELECT id_specialization from specialization where name_specialization = ?";
        try(Connection connection = AppConstant.ds.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) return resultSet.getInt("id_specialization");
        }catch(SQLException e){
            System.out.println("Ошибка SQL-запроса");
        }
        return 0;
    }
    public String getName(int id){
        String query = "SELECT name_specialization from specialization where id_specialization = ?";
        try(Connection connection = AppConstant.ds.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) return resultSet.getString("name_specialization");
        }catch(SQLException e){
            System.out.println("Ошибка SQL-запроса");
        }
        return null;
    }
}
