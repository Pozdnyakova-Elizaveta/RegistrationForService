package org.example;

import org.postgresql.util.PGInterval;

import java.math.BigDecimal;
import java.sql.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ServiceJdbcRepository {
    public void create(Service service){
        String sql = "INSERT into service (employee_id, name_service, price, lead_time) values (?, ?, ?, ?)";
        try(Connection connection = AppConstant.ds.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, service.getEmployeeId());
            statement.setString(2, service.getNameService());
            statement.setBigDecimal(3, service.getPrice());
            statement.setObject(4, service.getLeadTime(), Types.OTHER);
            int row = statement.executeUpdate();
            if (row>0) System.out.println("Услуга успешно создана");
            else System.out.println("Не удалось создать услугу");
        }catch(SQLException e){
            System.out.println("Ошибка SQL-запроса ");
        }
    }
    public void delete(int id){
        String sql = "DELETE from service where id_service = ?";
        try(Connection connection = AppConstant.ds.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            int row = statement.executeUpdate();
            if (row>0) System.out.println("Услуга успешно удалена");
            else System.out.println("Не удалось удалить услугу");
        } catch(SQLException e){
            System.out.println("Ошибка SQL-запроса");
        }
    }
    public void update(Service service){
        String sql = "UPDATE service set employee_id = ?, name_service = ?, price = ?, lead_time = ? where id_service = ?";
        try(Connection connection = AppConstant.ds.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, service.getEmployeeId());
            statement.setString(2, service.getNameService());
            statement.setBigDecimal(3, service.getPrice());
            statement.setObject(4, service.getLeadTime(), Types.OTHER);
            statement.setInt(5, service.getId());
            int row = statement.executeUpdate();
            if (row>0) System.out.println("Услуга успешно обнолвена");
            else System.out.println("Не удалось обновить услугу");
        }catch(SQLException e){
            System.out.println("Ошибка SQL-запроса");
        }

    }
    public List<Service> getByEmployee(int idEmployee){
        String sql = "SELECT * from service where employee_id = ?";
        List<Service> list = new ArrayList<>();
        try(Connection connection = AppConstant.ds.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, idEmployee);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Service service = Service.builder().id(resultSet.getInt(1)).employeeId(resultSet.getInt(2))
                        .nameService(resultSet.getString(3)).build();
                String price = resultSet.getString(4);
                price = price.replaceAll("[^\\d.,-]", "")
                        .replace(",", ".");
                service.setPrice(new BigDecimal(price));
                PGInterval interval = (PGInterval) resultSet.getObject(5);
                Duration duration = Duration.ofSeconds((long) (interval.getSeconds() + interval.getMinutes() * 60 + interval.getHours() * 3600 + interval.getDays() * 86400));
                service.setLeadTime(duration);
                list.add(service);
            }
        }catch(SQLException e){
            System.out.println("Ошибка SQL-запроса "+e.getMessage());
        }
        return list;
    }
}
