package org.example;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BookDAO extends BaseDAO<Book>{
    public BookDAO() {
        super(Book.class);
    }

    @Override
    public String getMessageSuccessfulCreation(Book entity) {
        return "Запись на услугу "+entity.getService().getNameService()+" успешно создана";
    }

    @Override
    public String getMessageFailedCreation(Book entity) {
        if (entity!=null && entity.getService()!=null && entity.getService().getNameService()!=null)
            return "Не удалось создать запись на услугу "+entity.getService().getNameService();
        else return "Не удалось создать запись на услугу";
    }

    @Override
    public String getMessageSuccessfulUpdate(Book entity) {
        return "Запись на услугу "+entity.getService().getNameService()+" успешно обновлена";
    }

    @Override
    public String getMessageFailedUpdate(Book entity) {
        if (entity!=null && entity.getService()!=null && entity.getService().getNameService()!=null)
            return "Не удалось обновить запись на услугу "+entity.getService().getNameService();
        else return "Не удалось обновить запись на услугу";
    }

    @Override
    public String getMessageSuccessfulDelete(int id) {
        return "Запись на услугу id="+id+" успешно удалена";
    }

    @Override
    public String getMessageFailedDelete(int id) {
        return "Не удалось удалить запись на услугу с id="+id;
    }

    @Override
    public String getMessageFailedGetById(int id) {
        return "Не удалось получить запись на услугу по id="+id;
    }
}
