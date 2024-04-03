package com.web.recruit.services;

import com.web.recruit.models.*;
import com.web.recruit.dao.VacancyDao;
import java.util.List;


public class VacancyService extends CommonService<Vacancy, VacancyDao> {
    public VacancyService() {
        super(new VacancyDao());
    }

    public List<Resume> findVacancyResumes(Vacancy obj) {
        return dao.findVacancyResumes(obj);
    }

    public List<Vacancy> vacancyFilter(List<String> vacancyNames, List<Company> companies, Long minSalary, Long maxSalary) {
        return dao.vacancyFilter(vacancyNames, companies, minSalary, maxSalary);
    }

}
