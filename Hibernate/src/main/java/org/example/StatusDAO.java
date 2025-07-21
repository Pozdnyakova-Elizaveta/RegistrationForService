package org.example;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;

import java.util.List;

public class StatusDAO extends BaseDAO<Status>{
    public StatusDAO() {
        super(Status.class);
    }

    @Override
    public String getMessageSuccessfulCreation(Status entity) {
        return "Статус "+entity.getName()+" успешно создан";
    }

    @Override
    public String getMessageFailedCreation(Status entity) {
        if (entity!=null && entity.getName()!=null) return "Не удалось создать статус "+entity.getName();
        else return "Не удалось создать статус";
    }

    @Override
    public String getMessageSuccessfulUpdate(Status entity) {
        return "Статус "+entity.getName()+" успешно обновлен";
    }

    @Override
    public String getMessageFailedUpdate(Status entity) {
        if (entity!=null && entity.getName()!=null) return "Не удалось обновить статус "+entity.getName();
        else return "Не удалось обновить статус";
    }

    @Override
    public String getMessageSuccessfulDelete(int id) {
        return "Статус id="+id+" успешно удален";
    }

    @Override
    public String getMessageFailedDelete(int id) {
        return "Не удалось удалить статус id="+id;
    }

    @Override
    public String getMessageFailedGetById(int id) {
        return "Не удалось получить статус по id="+id;
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
            throw new Exception("Статус по названию "+name+" не найден");
        }
    }
}