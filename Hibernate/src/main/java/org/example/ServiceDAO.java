package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ServiceDAO extends BaseDAO<Service>{
    public ServiceDAO() {
        super(Service.class);
    }

    @Override
    public String getMessageSuccessfulCreation(Service entity) {
        return "Услуга "+entity.getNameService()+" успешно создана";
    }

    @Override
    public String getMessageFailedCreation(Service entity) {
        if (entity!=null && entity.getNameService()!=null) return "Не удалось создать услугу "+entity.getNameService();
        else return "Не удалось создать услугу";
    }

    @Override
    public String getMessageSuccessfulUpdate(Service entity) {
        return "Услуга "+entity.getNameService()+" успешно обновлена";
    }

    @Override
    public String getMessageFailedUpdate(Service entity) {
        if (entity!=null && entity.getNameService()!=null) return "Не удалось обновить услугу "+entity.getNameService();
        else return "Не удалось обновить услугу";
    }

    @Override
    public String getMessageSuccessfulDelete(int id) {
        return "Услуга id="+id+" успешно удалена";
    }

    @Override
    public String getMessageFailedDelete(int id) {
        return "Не удалось удалить услугу с id="+id;
    }

    @Override
    public String getMessageFailedGetById(int id) {
        return "Не удалось получить услугу по id="+id;
    }
}
