package com.web.recruit.dao;

import com.web.recruit.models.*;
import com.web.recruit.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class AuthDao extends CommonDao<Auth> {

    public AuthDao() {
        super(Auth.class);
    }

    public Auth validateAuth(String mail, String password) {
        try (Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Auth b = session
                    .createQuery("from Auth where mail = :mail and password = :password", Auth.class)
                    .setParameter("mail", mail)
                    .setParameter("password", password)
                    .getSingleResult();
            t.commit();
            return b;
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }

    public Auth findByMail(String mail) {
        try (Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Auth b = session
                    .createQuery("from Auth where mail = :mail", Auth.class)
                    .setParameter("mail", mail)
                    .setMaxResults(1)
                    .getSingleResult();
            t.commit();
            return b;
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }

//    public Auth createAuth(String mail, String password, AuthRole role) {
//        try (Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession()) {
//            Transaction t = session.beginTransaction();
//            Auth b = session
//                    .createQuery("from Auth where mail = :mail and password = :password", Auth.class)
//                    .setParameter("mail", mail)
//                    .setParameter("password", password)
//                    .getSingleResult();
//            t.commit();
//            return b;
//        } catch (jakarta.persistence.NoResultException e) {
//            return null;
//        }
//    }
}
