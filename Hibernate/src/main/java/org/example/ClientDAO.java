package org.example;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.SelectionQuery;

import java.nio.channels.NonReadableChannelException;

public class ClientDAO {
    public void delete(int id){
        Session session = Main.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            Client client = session.find(Client.class, id);
            if (client!=null) session.remove(client);
            transaction.commit();
            System.out.println("Клиент успешно удален");
        } catch (Exception e){
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

    }
    public void create(Client client){
        Session session = Main.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(client);
            transaction.commit();
            System.out.println("Клиент успешно создан");
        }
        catch(Exception e){
            if (transaction != null && transaction.isActive()) transaction.rollback();
        }
        finally {
            session.close();
        }
    }
    public void update(Client client){
        Session session = Main.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(client);
            transaction.commit();
            System.out.println("Клиент успешно обновлен");
        }catch (Exception e){
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }
    public Client getByLogin(String login) throws Exception {
        String hql = "FROM Client WHERE login = :login";
        try (Session session = Main.sessionFactory.openSession()) {
            SelectionQuery<Client> query = session.createSelectionQuery(hql, Client.class);
            query.setParameter("login", login);
            Client client = query.getSingleResult();
            session.close();
            return client;
        } catch (NoResultException e) {
            throw new Exception("Клиент по логину не найден");
        }
    }

}
