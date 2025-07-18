package org.example;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.SelectionQuery;

public class EmployeeDAO {
    public void delete(int id){
        Session session = Main.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            Employee employee = session.find(Employee.class, id);
            if (employee!=null) session.remove(employee);
            transaction.commit();
            System.out.println("Сотрудник удален");

        } catch (Exception e){
            if (transaction!=null && transaction.isActive()) transaction.rollback();
        }
        finally {
            session.close();
        }
    }
    public void create(Employee employee){
        Session session = Main.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.persist(employee);
            transaction.commit();
            System.out.println("Сотрудник успешно создан");
        }catch (Exception e){
            if (transaction!=null && transaction.isActive()) transaction.rollback();
            System.out.println(e.getMessage());
        }
        finally {
            session.close();
        }
    }
    public void update(Employee employee){
        Session session = Main.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.merge(employee);
            transaction.commit();
            System.out.println("Сотрудник успешно обновлен");

        }catch (Exception e){
            if (transaction!=null && transaction.isActive()) transaction.rollback();
            System.out.println(e.getMessage());
        }
        finally {
            session.close();
        }
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
            throw new Exception("Сотрудник по логину не найден");
        }
    }
    public Employee getById(int id) throws Exception {
        try (Session session = Main.sessionFactory.openSession();){
            Employee employee = session.find(Employee.class, id);
            session.close();
            return employee;
        }catch (NoResultException e){
            throw new Exception("Сотрудник по id не найден");
        }
    }
}
