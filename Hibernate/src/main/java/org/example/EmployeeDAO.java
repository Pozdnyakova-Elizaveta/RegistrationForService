package org.example;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.SelectionQuery;

public class EmployeeDAO extends BaseDAO<Employee>{

    public EmployeeDAO() {
        super(Employee.class);
    }

    @Override
    public String getMessageSuccessfulCreation(Employee entity) {
        return "Сотрудник "+entity.getLogin()+" успешно создан";
    }

    @Override
    public String getMessageFailedCreation(Employee entity) {
        if (entity!=null && entity.getLogin()!=null) return "Не удалось создать сотрудника "+entity.getLogin();
        else return "Не удалось создать сотрудника";
    }

    @Override
    public String getMessageSuccessfulUpdate(Employee entity) {
        return "Сотрудник "+entity.getLogin()+" успешно обновлен";
    }

    @Override
    public String getMessageFailedUpdate(Employee entity) {
        if (entity!=null && entity.getLogin()!=null) return "Не удалось обновить сотрудника "+entity.getLogin();
        else return "Не удалось обновить сотрудника";
    }

    @Override
    public String getMessageSuccessfulDelete(int id) {
        return "Сотрудник id="+id+" успешно удален";
    }

    @Override
    public String getMessageFailedDelete(int id) {
        return "Не удалось удалить сотрудника с id="+id;
    }

    @Override
    public String getMessageFailedGetById(int id) {
        return "Не удалось получить сотрудника по id="+id;
    }
    public Employee getByLogin(String login) throws Exception {
        try (Session session = Main.sessionFactory.openSession();){
            String hql = "From Employee LEFT JOIN FETCH specializationList where login = :login";
            SelectionQuery<Employee> query = session.createSelectionQuery(hql, Employee.class);
            query.setParameter("login", login);
            Employee employee = query.getSingleResult();
            session.close();
            return employee;
        }catch (NoResultException e){
            throw new Exception("Сотрудник по логину "+login+" не найден");
        }
    }
}
