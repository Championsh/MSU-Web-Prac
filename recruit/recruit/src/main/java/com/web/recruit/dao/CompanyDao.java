package com.web.recruit.dao;

import com.web.recruit.models.*;

import java.util.List;
import java.util.Set;


public class CompanyDao extends CommonDao<Company> {

    public CompanyDao() {
        super(Company.class);
    }

    public List<Vacancy> findVacancies(Company obj) {
        return obj.getVacancies();
    }

    public Set<Applicant> findApplicants(Company obj) {
        return obj.getApplicants();
    }
}