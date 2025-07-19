package org.example;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;

import java.util.List;

public class StatusDAO{
    public List<Status> getAll(){
        Session session = Main.sessionFactory.openSession();
        Query<Status> query = session.createQuery("FROM Status", Status.class);
        List<Status> statusList = query.getResultList();
        session.close();
        return statusList;
    }
    public void create(Status status){
        Session session = Main.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.persist(status);
            transaction.commit();
            System.out.println("Статус успешно создан");
        } catch (Exception e){
            if (transaction!=null && transaction.isActive()) transaction.rollback();
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }
    public void delete(int id){
        Session session = Main.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            Status status = session.find(Status.class, id);
            if (status!=null) session.remove(status);
            transaction.commit();
            System.out.println("Статус успешно удален");
        } catch (Exception e){
            if (transaction!=null && transaction.isActive()) transaction.rollback();
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }
    public int getId(String name) throws Exception {
        String hql = "SELECT id from Status where name = :name";
        try(Session session = Main.sessionFactory.openSession()) {
            Query<Integer> query = session.createQuery(hql, Integer.class);
            query.setParameter("name", name);
            int id = query.getSingleResult();
            session.close();
            return id;
        }catch (NoResultException e){
            throw new Exception("Статус по имени не найден");
        }
    }
    public Status getById(int id) throws Exception {
        try(Session session = Main.sessionFactory.openSession()) {
            Status status = session.find(Status.class, id);
            return status;
        }catch (NoResultException e){
            throw new Exception("Статус по id не найден");
        }
    }
}