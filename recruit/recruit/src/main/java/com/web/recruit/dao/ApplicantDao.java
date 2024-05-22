package com.web.recruit.dao;

import com.web.recruit.models.*;
import com.web.recruit.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;


public class ApplicantDao extends CommonDao<Applicant> {

    public ApplicantDao() {
        super(Applicant.class);
    }

    public List<Experience> findExperiences(Applicant obj) {
        return obj.getExperiences();
    }

    public List<Education> findEducations(Applicant obj) {
        return obj.getEducations();
    }

    public List<Resume> findResumes(Applicant obj) {
        return obj.getResumes();
    }

    public Set<Company> findCompanies(Applicant obj) {
        return obj.getCompanies();
    }

    public Applicant findByAuth(Auth auth) {
        try (Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Applicant b = session
                    .createQuery("from Applicant where auth = :auth", Applicant.class)
                    .setParameter("auth", auth)
                    .setMaxResults(1)
                    .getSingleResult();
            t.commit();
            return b;
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }
}