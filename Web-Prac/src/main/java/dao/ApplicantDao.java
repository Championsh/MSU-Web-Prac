package dao;

import models.Applicant;
import models.Resume;
import models.Education;
import models.Experience;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionFactoryUtil;
import java.util.List;

public class ApplicantDao {

    public Applicant findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Applicant.class, id);
    }

    public void save(Applicant applicant) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(applicant);
        tx1.commit();
        session.close();
    }

    public void update(Applicant applicant) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(applicant);
        tx1.commit();
        session.close();
    }

    public void delete(Applicant applicant) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(applicant);
        tx1.commit();
        session.close();
    }

    public Experience findExperiencesById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Experience.class, id);
    }

    public List<Resume> findResumesByUser(Applicant applicant) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query<Resume> query = session.createQuery("FROM Resume WHERE applicant = :applicant", Resume.class);
            query.setParameter("applicant", applicant);
            return query.getResultList();
        }
    }
}