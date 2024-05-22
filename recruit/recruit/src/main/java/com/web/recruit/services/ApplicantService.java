package com.web.recruit.services;

import com.web.recruit.models.*;
import com.web.recruit.dao.ApplicantDao;

import com.web.recruit.models.Applicant;
import com.web.recruit.models.Company;
import com.web.recruit.models.Education;
import java.util.List;
import java.util.Set;


public class ApplicantService extends CommonService<Applicant, ApplicantDao> {
    public ApplicantService(){
        super(new ApplicantDao());
    }

    public List<Experience> findExperiences(Applicant obj) {
        return dao.findExperiences(obj);
    }

    public List<Education> findEducations(Applicant obj) {
        return dao.findEducations(obj);
    }

    public List<Resume> findResumes(Applicant obj) {
        return dao.findResumes(obj);
    }

    public Set<Company> findCompanies(Applicant obj) {
        return dao.findCompanies(obj);
    }

    public Applicant findByAuth(Auth auth) {
        return dao.findByAuth(auth);
    }
}
