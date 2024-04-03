package com.web.recruit.services;

import com.web.recruit.models.*;
import com.web.recruit.dao.CompanyDao;

import com.web.recruit.models.Company;
import java.util.List;
import java.util.Set;


public class CompanyService extends CommonService<Company, CompanyDao> {
    public CompanyService(){
        super(new CompanyDao());
    }

    public List<Vacancy> findVacancies(Company obj) {
        return dao.findVacancies(obj);
    }

    public Set<Applicant> findApplicants(Company obj) {
        return dao.findApplicants(obj);
    }
}
