package org.example;

import java.sql.*;

public class ClientJdbcRepository {
    public void delete(int id){
        String query = "DELETE from client where id_client = ?";
        try(Connection connection = AppConstant.ds.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, id);
            int row = statement.executeUpdate();
            if (row>0) System.out.println("Запись о клиенте удалена");
            else System.out.println("Не удалось удалить запись");
        }catch(SQLException e){
            System.out.println("Ошибка SQL - не удалось удалить запись");
        }
    }
    public void create(Client client){
        String query = "INSERT INTO client (first_name, last_name, email, password, login) values (?, ?, ?, ?, ?)";
        try(Connection connection = AppConstant.ds.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, client.getFirstName());
            statement.setString(2, client.getLastName());
            statement.setString(3, client.getEmail());
            statement.setString(4, client.getPassword());
            statement.setString(5, client.getLogin());
            int row = statement.executeUpdate();
            if (row>0) System.out.println("Запись о клиенте создага");
            else System.out.println("Не удалось создать запись");
        }catch(SQLException e){
            System.out.println("Ошибка SQL - не удалось создать аккаунт ");
        }
    }
    public void update(Client client){
        String sql = "UPDATE client SET first_name = ?, last_name = ?, login = ?, password = ?, email = ? WHERE id_client = ?";
        try(Connection connection = AppConstant.ds.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, client.getFirstName());
            statement.setString(2, client.getLastName());
            statement.setString(3, client.getLogin());
            statement.setString(4, client.getPassword());
            statement.setString(5, client.getEmail());
            statement.setInt(6, client.getId());
            int row = statement.executeUpdate();
            if (row>0) System.out.println("Данные обновлены");
            else System.out.println("Пользователь не найден");
        }catch (SQLException e){
            System.out.println("Ошибка SQL-запроса");
        }

    }

    public void auth(String login, String password) {
        Client client = getByLogin(login);
        if (client == null) System.out.println("Логин введен неверно");
        else if (client.getPassword().equals(password)) System.out.println("Вход успешно выполнен!");
        else System.out.println("Пароль введен неверно");
    }
    public Client getByLogin(String login){
        Client client = null;
        String query = "SELECT * from client where login = ?";
        try(Connection connection = AppConstant.ds.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                client = Client.builder().id(resultSet.getInt("id_client"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .login(resultSet.getString("login"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password")).build();
            }
            else System.out.println("Не удалось найти аккаунт");
        } catch (SQLException e){
            System.out.println("Ошибка SQL-запроса");
        }
        return client;
    }
}
