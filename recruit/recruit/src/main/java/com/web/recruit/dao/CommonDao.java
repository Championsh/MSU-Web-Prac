package com.web.recruit.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import com.web.recruit.utils.HibernateSessionFactory;


public abstract class CommonDao<T> {
    private final Class<T> entityClass;

    public CommonDao(Class<T> entityClass){
        this.entityClass = entityClass;
    }

    public T findById(Long id){
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        T b = session.get(entityClass, id);
        t.commit();
        return b;
    }

    public List<T> findAll(){
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        session.flush();
        List<T> b = session.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
        t.commit();
        return b;
    }

    public void save(T obj){
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        session.persist(obj);
        session.flush();
        t.commit();
        session.close();
    }

    public void update(T obj){
        HibernateSessionFactory.getSessionFactory()
                .inTransaction(session -> {
                    session.merge(obj);
                    session.flush();
                });
    }

    public void delete(T obj){
        HibernateSessionFactory.getSessionFactory()
                .inTransaction(session -> {
                    session.remove(obj);
                    session.flush();
                });
    }

    public void deleteById(Long id){
        HibernateSessionFactory.getSessionFactory()
                .inTransaction(session -> {
                    session.remove(findById(id));
                    session.flush();
                });
    }
}
