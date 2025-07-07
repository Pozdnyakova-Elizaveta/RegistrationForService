package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.ArrayList;

public class StatusJdbcRepository {
    public ArrayList<String> getAll(){
        ArrayList<String> statuses = new ArrayList<>();
        String query = "SELECT * from status";
        try (Connection connection = AppConstant.ds.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                statuses.add(resultSet.getString("name_status"));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка SQL-запроса");
        }
        return statuses;
    }
    public int getId(String name) {
        String query = "SELECT id_status from status s where s.name_status = ?";
        try (Connection connection = AppConstant.ds.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_status");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка SQL-запроса");
        }
        return 0;
    }
}
