package com.web.recruit.dao;

import jakarta.persistence.TypedQuery;
import com.web.recruit.models.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import com.web.recruit.utils.HibernateSessionFactory;

public class VacancyDao extends CommonDao<Vacancy> {

    public VacancyDao() {
        super(Vacancy.class);
    }


    public List<Resume> findVacancyResumes(Vacancy obj) {
        try (Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            StringBuilder queryString = new StringBuilder("SELECT r FROM Resume r ");
            queryString.append("WHERE r.desiredPosition = :vacancyName ");
            if (obj.getSalary() != null) queryString.append("AND r.desiredSalary <= :salary ");

            TypedQuery<Resume> query = session.createQuery(queryString.toString(), Resume.class);

            query.setParameter("vacancyName", obj.getVacancyName());
            if (obj.getSalary() != null) query.setParameter("salary", obj.getSalary());

            List<Resume> resumes = query.getResultList();
            List<Resume> res = new ArrayList<>();

            List<String> requirements = List.of(obj.getRequirements().split(";"));
            for (Resume resume: resumes) {
                boolean flag = true;
                StringJoiner educationsInfo = new StringJoiner("\n");
                for (EducationResume educationResume: resume.getEducationResumes()) educationsInfo.add(educationResume.getAdditionalInfo());

                StringJoiner experiencesInfo = new StringJoiner("\n");
                for (ExperienceResume experienceResume: resume.getExperienceResumes()) experiencesInfo.add(experienceResume.getAdditionalInfo());

                String resumeInfo = experiencesInfo + ";\n" + educationsInfo;

                for (String requirement: requirements) {
                    if (!resumeInfo.toLowerCase().contains(requirement.toLowerCase())) {
                        flag = false;
                        break;
                    }
                }
                if (flag)
                    res.add(resume);
            }

            t.commit();
            return res;
        }
    }

    public List<Vacancy> vacancyFilter(List<String> vacancyNames, List<Company> companies, Long minSalary, Long maxSalary) {
        try (Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            StringBuilder queryString = new StringBuilder("SELECT DISTINCT v FROM Vacancy v");
//            if (companies != null && !companies.isEmpty())
//                queryString.append(" LEFT JOIN v.company c");

            queryString.append(" WHERE TRUE");

            if (vacancyNames != null && !vacancyNames.isEmpty()) queryString.append(" AND v.vacancyName IN :vacancyNames");
            if (companies != null && !companies.isEmpty()) queryString.append(" AND v.company IN :companies");
            if (minSalary != null) queryString.append(" AND v.salary >= :minSalary");
            if (maxSalary != null) queryString.append(" AND v.salary <= :maxSalary");

            TypedQuery<Vacancy> query = session.createQuery(queryString.toString(), Vacancy.class);

            if (vacancyNames != null && !vacancyNames.isEmpty()) query.setParameter("vacancyNames", vacancyNames);
            if (companies != null && !companies.isEmpty()) query.setParameter("companies", companies);
            if (minSalary != null) query.setParameter("minSalary", minSalary);
            if (maxSalary != null) query.setParameter("maxSalary", maxSalary);

            List<Vacancy> res = query.getResultList();
            t.commit();
            return res;
        }
    }

}
