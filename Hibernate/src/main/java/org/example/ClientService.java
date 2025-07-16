package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.SelectionQuery;

import java.util.List;

public class ClientService {
    public void delete(int id){
        Session session = Main.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            Client client = session.find(Client.class, id);
            if (client!=null) session.remove(client);
            transaction.commit();
            System.out.println("Клиент успешно удален");
        } catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

    }
    public void create(Client client){
        Session session = Main.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(client);
        transaction.commit();
        session.close();
        System.out.println("Клиент успешно создан");
    }
    public void update(Client client){
        Session session = Main.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(client);
        transaction.commit();
        session.close();
        System.out.println("Клиент успешно обновлен");
    }
    public void auth(String login, String password){
        try {
            Client client = getByLogin(login);
            if (client.getPassword().equals(password)) System.out.println("Вход успешен!");
            else System.out.println("Пароль введен неверно!");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    public Client getByLogin(String login) throws Exception {
        String hql = "FROM Client WHERE login = :login";
        Session session = Main.sessionFactory.openSession();
        SelectionQuery<Client> query = session.createSelectionQuery(hql, Client.class);
        query.setParameter("login", login);
        List<Client> clients = query.list();
        if (clients.get(0)!=null) return clients.get(0);
        else throw new Exception("Клиентс данным логином не найден!");
    }

}
