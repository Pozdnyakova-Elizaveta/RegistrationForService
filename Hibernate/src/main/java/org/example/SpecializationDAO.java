package org.example;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;

import java.util.List;

public class SpecializationDAO {
    public List<Specialization> getAll(){
        String hql = "FROM Specialization";
        Session session = Main.sessionFactory.openSession();
        Query<Specialization> query = session.createQuery(hql, Specialization.class);
        List<Specialization> specializations = query.getResultList();
        session.close();
        return specializations;
    }
    public void create(Specialization specialization){
        Session session = Main.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(specialization);
            transaction.commit();
            System.out.println("Специализация добавлена");
        } catch(Exception e){
            if (transaction != null && transaction.isActive()) transaction.rollback();
        }
        finally {
            session.close();
        }
    }
    public void delete(int id){
        Session session = Main.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Specialization specialization = session.find(Specialization.class, id);
            if (specialization != null) {
                session.remove(specialization);
                transaction.commit();
                System.out.println("Специализация успешно удалена");
            }
            else throw new IllegalArgumentException("Специализация с ID " + id + " не найдена");
        } catch (IllegalArgumentException e){
            if (transaction != null && transaction.isActive()) transaction.rollback();
            throw e;
        } catch (Exception e){
            if (transaction != null && transaction.isActive()) transaction.rollback();
        } finally {
            session.close();
        }
    }
    public int getId(String name) throws Exception {
        try (Session session = Main.sessionFactory.openSession()) {
            String hql = "SELECT id FROM Specialization WHERE name = :name";
            SelectionQuery<Integer> query = session.createSelectionQuery(hql, Integer.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new Exception("Специализация по имени не найдена");
        }
    }
    public Specialization getById(int id) throws Exception {
        try (Session session = Main.sessionFactory.openSession()) {
            return session.find(Specialization.class, id);
        } catch (NoResultException e) {
            throw new Exception("Специализация по id не найдена");
        }

    }
}
