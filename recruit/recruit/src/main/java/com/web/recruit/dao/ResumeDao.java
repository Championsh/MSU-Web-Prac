package com.web.recruit.dao;

import jakarta.persistence.TypedQuery;
import com.web.recruit.models.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import com.web.recruit.utils.HibernateSessionFactory;

public class ResumeDao extends CommonDao<Resume> {

    public ResumeDao() {
        super(Resume.class);
    }

    public List<ExperienceResume> findResumeExperiences(Resume obj) {
        return obj.getExperienceResumes();
    }

    public List<EducationResume> findEducationResumes(Resume obj) {
        return obj.getEducationResumes();
    }

    public List<Vacancy> findResumeVacancies(Resume obj) {
        try (Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            StringBuilder queryString = new StringBuilder("SELECT v FROM Vacancy v ");
            queryString.append("WHERE v.vacancyName = :desiredPosition ");
            if (obj.getDesiredSalary() != null) queryString.append("AND v.salary >= :desiredSalary ");
//            queryString.append("AND v.requirements IN :resumeInfo ");

            TypedQuery<Vacancy> query = session.createQuery(queryString.toString(), Vacancy.class);

            query.setParameter("desiredPosition", obj.getDesiredPosition());
            if (obj.getDesiredSalary() != null) query.setParameter("desiredSalary", obj.getDesiredSalary());

            StringJoiner experiencesInfo = new StringJoiner("\n");
            for (ExperienceResume experience: obj.getExperienceResumes()) experiencesInfo.add(experience.getAdditionalInfo());

            StringJoiner educationsInfo = new StringJoiner("\n");
            for (EducationResume education: obj.getEducationResumes()) educationsInfo.add(education.getAdditionalInfo());

            String resumeInfo = experiencesInfo + ";\n" + educationsInfo;
//            query.setParameter("resumeInfo", resumeInfo);

            List<Vacancy> vacancies = query.getResultList();
            List<Vacancy> res = new ArrayList<>();
            for (Vacancy vacancy: vacancies) {
                List<String> requirements = List.of(vacancy.getRequirements().split(";"));
                boolean flag = true;
                for (String requirement: requirements) {
                    if (!resumeInfo.toLowerCase().contains(requirement.toLowerCase())) {
                        flag = false;
                        break;
                    }
                }
                if (flag)
                    res.add(vacancy);
            }

            t.commit();
            return res;
        }
    }

    public List<Resume> resumeFilter(List<String> desiredPositions, List<Company> companies, Long minSalary, Long maxSalary, List<String> positions, List<Edlevel> edLevels, List<String> institutes, List<String> faculties, List<City> cities) {
        try (Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            StringBuilder queryString = new StringBuilder("SELECT DISTINCT r FROM Resume r");
            if ((positions != null && !positions.isEmpty()) ||
                (companies != null && !companies.isEmpty()) ||
                (minSalary != null) ||
                (maxSalary != null)) {
                queryString.append(" LEFT JOIN r.experiences ex");
            }

            if ((edLevels != null && !edLevels.isEmpty()) ||
                (institutes != null && !institutes.isEmpty()) ||
                (faculties != null && !faculties.isEmpty())) {
                queryString.append(" LEFT JOIN r.educations ed");
            }

            queryString.append(" WHERE TRUE");

            if (positions != null && !positions.isEmpty()) queryString.append(" AND ex.position IN :positions");
//            if (companies != null && !companies.isEmpty()) queryString.append(" AND ex.company IN :companies");
            if (minSalary != null) queryString.append(" AND ex.salary >= :minSalary");
            if (maxSalary != null) queryString.append(" AND ex.salary <= :maxSalary");
            if (desiredPositions != null && !desiredPositions.isEmpty()) queryString.append(" AND r.desiredPosition IN :desiredPositions");
            if (edLevels != null && !edLevels.isEmpty()) queryString.append(" AND ed.edlevel IN :edLevels");
            if (institutes != null && !institutes.isEmpty()) queryString.append(" AND ed.institute IN :institutes");
            if (faculties != null && !faculties.isEmpty()) queryString.append(" AND ed.faculty IN :faculties");

            TypedQuery<Resume> query = session.createQuery(queryString.toString(), Resume.class);

            if (positions != null && !positions.isEmpty()) query.setParameter("positions", positions);

            List<String> company_names = new ArrayList<>();
            if (companies != null && !companies.isEmpty()) {
                for (Company company: companies) company_names.add(company.getCompanyName());
//                query.setParameter("companies", company_names);
            }
            if (minSalary != null) query.setParameter("minSalary", minSalary);
            if (maxSalary != null) query.setParameter("maxSalary", maxSalary);
            if (desiredPositions != null && !desiredPositions.isEmpty()) query.setParameter("desiredPositions", desiredPositions);
            if (edLevels != null && !edLevels.isEmpty()) query.setParameter("edLevels", edLevels);
            if (institutes != null && !institutes.isEmpty()) query.setParameter("institutes", institutes);
            if (faculties != null && !faculties.isEmpty()) query.setParameter("faculties", faculties);

            List<Resume> resumes = query.getResultList();
            List<Resume> res = new ArrayList<>();

            if (cities != null && !cities.isEmpty()) {
                for (Resume resume: resumes) {
                    if (cities.contains(resume.getApplicant().getCity()))
                        res.add(resume);
                }
            } else {
                res = resumes;
            }

            t.commit();

            List<String> names = new ArrayList<>();
            for (Resume resume: res) {
                Set<Experience> exps = resume.getExperiences();

                for (Experience exp: exps) names.add(exp.getCompany());
            }

            return res;
        }
    }

}
