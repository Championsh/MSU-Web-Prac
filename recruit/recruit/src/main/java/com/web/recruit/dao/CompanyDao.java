package com.web.recruit.dao;

import com.web.recruit.models.*;
import com.web.recruit.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;


public class CompanyDao extends CommonDao<Company> {

    public CompanyDao() {
        super(Company.class);
    }

    public List<Vacancy> findVacancies(Company obj) {
        return obj.getVacancies();
    }

    public List<ApplicantCompany> findApplicantCompanies(Company obj) {
        return obj.getApplicantCompanies();
    }

    public Company findByAuth(Auth auth) {
        try (Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Company b = session
                    .createQuery("from Company where auth = :auth", Company.class)
                    .setParameter("auth", auth.getAuthId())
                    .setMaxResults(1)
                    .getSingleResult();
            t.commit();
            return b;
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }
}