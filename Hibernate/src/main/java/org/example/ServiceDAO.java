package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ServiceDAO {
    public void create(Service service){
        Session session = Main.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.persist(service);
            transaction.commit();
            System.out.println("Услуга успешно создана");
        }catch (Exception e){
            if (transaction!=null && transaction.isActive()){
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    public void delete(int id) {
        Session session = Main.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Service service = session.find(Service.class, id);
            if (service!=null) session.remove(service);
            transaction.commit();
            System.out.println("Сервис успешно удален");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }
    public void update(Service service){
        Session session = Main.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(service);
            transaction.commit();
            System.out.println("Услуга обновлена");
        } catch (Exception e){
            if (transaction!=null && transaction.isActive()){
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }
    public List<Service> getAll(){
        Session session = Main.sessionFactory.openSession();
        Query<Service> query = session.createQuery("FROM Service", Service.class);
        List<Service> serviceList = query.getResultList();
        session.close();
        return serviceList;
    }
}
