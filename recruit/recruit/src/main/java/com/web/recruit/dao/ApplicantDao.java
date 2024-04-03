package com.web.recruit.dao;

import com.web.recruit.models.*;

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
}