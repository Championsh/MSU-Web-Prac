package com.web.recruit.services;

import com.web.recruit.models.*;
import com.web.recruit.dao.ResumeDao;
import java.util.List;
import java.util.Set;


public class ResumeService extends CommonService<Resume, ResumeDao> {
    public ResumeService() {
        super(new ResumeDao());
    }

    public List<ExperienceResume> findResumeExperiences(Resume obj) {
        return dao.findResumeExperiences(obj);
    }

    public List<EducationResume> findEducationResumes(Resume obj) {
        return dao.findEducationResumes(obj);
    }

    public List<Vacancy> findResumeVacancies(Resume obj) {
        return dao.findResumeVacancies(obj);
    }

    public List<Resume> resumeFilter(List<String> desiredPositions, List<Company> companies, Long minSalary, Long maxSalary, List<String> positions, List<Edlevel> edLevels, List<String> institutes, List<String> faculties) {
        return dao.resumeFilter(desiredPositions, companies, minSalary, maxSalary, positions, edLevels, institutes, faculties);
    }

}
