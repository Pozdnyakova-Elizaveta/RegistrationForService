package org.example;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.SelectionQuery;

import java.nio.channels.NonReadableChannelException;

public class ClientDAO extends BaseDAO<Client>{
    public ClientDAO() {
        super(Client.class);
    }

    @Override
    public String getMessageSuccessfulCreation(Client entity) {
        return "Клиент "+entity.getLogin()+" успешно создан";
    }

    @Override
    public String getMessageFailedCreation(Client entity) {
        if (entity!=null && entity.getLogin()!=null) return "Не удалось создать клиента "+entity.getLogin();
        else return "Не удалось создать клиента";
    }

    @Override
    public String getMessageSuccessfulUpdate(Client entity) {
        return "Клиент "+entity.getLogin()+" успешно обновлен";
    }

    @Override
    public String getMessageFailedUpdate(Client entity) {
        if (entity!=null && entity.getLogin()!=null) return "Не удалось обновить клиента "+entity.getLogin();
        else return "Не удалось обновить клиента";
    }

    @Override
    public String getMessageSuccessfulDelete(int id) {
        return "Клиент id="+id+" успешно удален";
    }

    @Override
    public String getMessageFailedDelete(int id) {
        return "Не удалось удалить клиента с id="+id;
    }

    @Override
    public String getMessageFailedGetById(int id) {
        return "Не удалось получить клиента по id="+id;
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
            throw new Exception("Клиент по логину "+login+" не найден");
        }
    }

}
