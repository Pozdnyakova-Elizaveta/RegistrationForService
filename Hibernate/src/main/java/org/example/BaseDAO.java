package org.example;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public abstract class BaseDAO <T extends BaseEntity>{
    private final Class<T> entityClass;

    public BaseDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public abstract String getMessageSuccessfulCreation(T entity);
    public abstract String getMessageFailedCreation(T entity);
    public abstract String getMessageSuccessfulUpdate(T entity);
    public abstract String getMessageFailedUpdate(T entity);
    public abstract String getMessageSuccessfulDelete(int id);
    public abstract String getMessageFailedDelete(int id);
    public abstract String getMessageFailedGetById(int id);
    public void create(T entity){
        Session session = Main.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
            System.out.println(getMessageSuccessfulCreation(entity));
        }
        catch(Exception e){
            if (transaction != null && transaction.isActive()) transaction.rollback();
            System.out.println(getMessageFailedCreation(entity)+": "+e.getMessage());
        }
        finally {
            session.close();
        }
    }
    public void update(T entity){
        Session session = Main.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(entity);
            transaction.commit();
            System.out.println(getMessageSuccessfulUpdate(entity));
        }catch (Exception e){
            if (transaction != null && transaction.isActive()) transaction.rollback();
            System.out.println(getMessageFailedUpdate(entity)+": "+e.getMessage());
        } finally {
            session.close();
        }
    }
    public void delete(int id){
        Session session = Main.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            T entity = session.find(entityClass, id);
            if (entity!=null) {
                session.remove(entity);
                transaction.commit();
                System.out.println(getMessageSuccessfulDelete(id));
            }
            else throw new NoResultException("Не удалось найти запись по id");
        } catch (Exception e){
            if (transaction != null && transaction.isActive()) transaction.rollback();
            System.out.println(getMessageFailedDelete(id)+": "+e.getMessage());
        } finally {
            session.close();
        }
    }
    public T getById(int id) throws Exception {
        try (Session session = Main.sessionFactory.openSession();){
            T entity = session.find(entityClass, id);
            return entity;
        }catch (NoResultException e){
            throw new Exception(getMessageFailedGetById(id)+": "+e.getMessage());
        }
    }
    public List<T> getAll(){
        Session session = Main.sessionFactory.openSession();
        Query<T> query = session.createQuery("FROM "+entityClass.getName(), entityClass);
        List<T> list = query.getResultList();
        session.close();
        return list;
    }
}
