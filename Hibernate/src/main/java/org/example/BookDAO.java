package org.example;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BookDAO {
    public void create(Book book){
        Session session = Main.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.persist(book);
            transaction.commit();
            System.out.println("Запись на услугу успешно создана");
        }catch(Exception e){
            if (transaction!=null && transaction.isActive()) transaction.rollback();
        }finally {
            session.close();
        }
    }
    public void delete(int id) {
        Session session = Main.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            Book book = session.find(Book.class, id);
            if (book !=null) session.remove(book);
            transaction.commit();
            System.out.println("Запись на услугу успешно удалена");
        }catch(Exception e){
            if (transaction!=null && transaction.isActive()) transaction.rollback();
        }finally {
            session.close();
        }
    }
    public void update(Book book){
        Session session = Main.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.merge(book);
            transaction.commit();
            System.out.println("Запись на услугу спешно обновлена");

        }catch(Exception e){
            if (transaction!=null && transaction.isActive()) transaction.rollback();
        }finally {
            session.close();
        }
    }
    public List<Book> getAll(){
        Session session = Main.sessionFactory.openSession();
        Query<Book> query = session.createQuery("FROM Book", Book.class);
        List<Book> bookList = query.getResultList();
        session.close();
        return bookList;
    }
}
