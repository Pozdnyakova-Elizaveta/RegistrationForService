package org.example;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.SelectionQuery;


public class SpecializationDAO extends BaseDAO<Specialization>{

    public SpecializationDAO() {
        super(Specialization.class);
    }

    @Override
    public String getMessageSuccessfulCreation(Specialization entity) {
        return "Специализация "+entity.getName()+" успешно создана";
    }

    @Override
    public String getMessageFailedCreation(Specialization entity) {
        if (entity!=null && entity.getName()!=null) return "Не удалось создать специализацию "+entity.getName();
        else return "Не удалось создать специализацию";
    }

    @Override
    public String getMessageSuccessfulUpdate(Specialization entity) {
        return "Специализация "+entity.getName()+" успешно обновлена";
    }

    @Override
    public String getMessageFailedUpdate(Specialization entity) {
        if (entity!=null && entity.getName()!=null) return "Не удалось обновить специализацию "+entity.getName();
        else return "Не удалось обновить специализацию";
    }

    @Override
    public String getMessageSuccessfulDelete(int id) {
        return "Специализация id="+id+" успешно удалена";
    }

    @Override
    public String getMessageFailedDelete(int id) {
        return "Не удалось удалить специализацию с id="+id;
    }

    @Override
    public String getMessageFailedGetById(int id) {
        return "Не удалось получить специализацию по id="+id;
    }
    public int getId(String name) throws Exception {
        try (Session session = Main.sessionFactory.openSession()) {
            String hql = "SELECT id FROM Specialization WHERE name = :name";
            SelectionQuery<Integer> query = session.createSelectionQuery(hql, Integer.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new Exception("Специализация по названию "+name+" не найдена");
        }
    }
}
