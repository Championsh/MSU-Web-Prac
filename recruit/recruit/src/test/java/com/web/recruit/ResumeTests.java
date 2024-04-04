package com.web.recruit;

import org.junit.jupiter.api.Test;
import com.web.recruit.models.*;
import com.web.recruit.services.*;
import org.junit.jupiter.api.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class ResumeTests {

    private final ApplicantService applicantService = new ApplicantService();
    private final Applicant applicant = applicantService.findById(1);

    @Test
    public void testResume() {
        String desiredPosition = "Директор по маркетингу";
        Long salary = 550000L;

        Resume resume = new Resume(applicant, desiredPosition, salary);
        Assertions.assertEquals(resume.getApplicant(), applicant);
        Assertions.assertEquals(resume.getDesiredPosition(), desiredPosition);
        Assertions.assertEquals(resume.getDesiredSalary(), salary);
    }

    @Test
    public void testFindById() {
        ResumeService resumeService = new ResumeService();
        Resume resume = resumeService.findById(1L);
        Assertions.assertEquals(resume.getResumeId(), 1L);
    }

    @Test
    public void testFindAll() {
        ResumeService resumeService = new ResumeService();
        List<Resume> Resume = resumeService.findAll();
        Assertions.assertEquals(Resume.size(), 2);

        for (int i = 0; i < Resume.size(); i++) {
            Resume cur_resume = resumeService.findById(i + 1);
            Applicant cur_applicant = cur_resume.getApplicant();
            String cur_position = cur_resume.getDesiredPosition();
            Long cur_salary = cur_resume.getDesiredSalary();
            Assertions.assertEquals(Resume.get(i).getApplicant(), cur_applicant);
            Assertions.assertEquals(Resume.get(i).getDesiredPosition(), cur_position);
            Assertions.assertEquals(Resume.get(i).getDesiredSalary(), cur_salary);
        }
    }

    @Test
    public void testDeleteById() {
        String desiredPosition = "Директор по маркетингу";
        Long salary = 550000L;

        Resume tmp_resume = new Resume(applicant, desiredPosition, salary);
        ResumeService ResumeService = new ResumeService();
        ResumeService.save(tmp_resume);
        Resume checK_resume = ResumeService.findById(tmp_resume.getResumeId());
        Assertions.assertEquals(tmp_resume, checK_resume);

        ResumeService.deleteById(tmp_resume.getResumeId());
        checK_resume = ResumeService.findById(tmp_resume.getResumeId());
        Assertions.assertNull(checK_resume);
    }

    @Test
    public void testFindResumeExperiences() {
        ResumeService resumeService = new ResumeService();
        Resume resume = resumeService.findById(2);
        List<ExperienceResume> findResumeExperiences = resumeService.findResumeExperiences(resume);

        Assertions.assertEquals(findResumeExperiences.size(), 1);
        Assertions.assertEquals(findResumeExperiences.getFirst().getResume().getResumeId(), 2);
    }

    @Test
    public void testFindEducationResumes() {
        ResumeService resumeService = new ResumeService();
        Resume resume = resumeService.findById(2);
        List<EducationResume> findEducationResumes = resumeService.findEducationResumes(resume);

        Assertions.assertEquals(findEducationResumes.size(), 1);
        Assertions.assertEquals(findEducationResumes.getFirst().getResume().getResumeId(), 2);
    }

    @Test
    public void testFindResumeVacancies() {
        ResumeService resumeService = new ResumeService();
        Resume resume = resumeService.findById(2);
        List<Vacancy> findResumeVacancies = resumeService.findResumeVacancies(resume);

        Assertions.assertEquals(1, findResumeVacancies.size());
        Assertions.assertEquals(2, findResumeVacancies.getFirst().getVacancyId());

        String desiredPosition = "Директор по развитию";
        Long salary = 550000L;
        ApplicantService ApplicantService = new ApplicantService();
        Applicant Applicant = ApplicantService.findById(2L);

        Resume tmp_resume = new Resume(Applicant, desiredPosition, salary);
        ResumeService ResumeService = new ResumeService();
        ResumeService.save(tmp_resume);
        Resume checK_resume = ResumeService.findById(tmp_resume.getResumeId());
        Assertions.assertEquals(tmp_resume, checK_resume);

        findResumeVacancies = resumeService.findResumeVacancies(tmp_resume);
        Assertions.assertEquals(0, findResumeVacancies.size());

        ResumeService.deleteById(tmp_resume.getResumeId());
        checK_resume = ResumeService.findById(tmp_resume.getResumeId());
        Assertions.assertNull(checK_resume);

    }

    @Test
    public void testResumeFilter() {
        ResumeService ResumeService = new ResumeService();
        CompanyService CompanyService = new CompanyService();
        EdlevelService EdlevelService = new EdlevelService();
        CityService CityService = new CityService();

        List<Company> Companies = new ArrayList<>();
        Companies.add(CompanyService.findById(1));
        Companies.add(CompanyService.findById(2));

        List<String> desiredPositions = new ArrayList<>();
        desiredPositions.add("Директор по развитию");
        desiredPositions.add("Руководитель отдела аналитики");

        List<String> Positions = new ArrayList<>();
        Positions.add("Мидл Python разработчик");
        Positions.add("Руководитель отдела поставок");

        List<Edlevel> edLevels = new ArrayList<>();
        edLevels.add(EdlevelService.findById(1));

        List<String> institutes = new ArrayList<>();
        institutes.add("МГУ им М.В. Ломоносова");

        List<String> faculties = new ArrayList<>();
        faculties.add("ВМК");

        List<City> cities = new ArrayList<>();
        cities.add(CityService.findById(2));

        List<Resume> resumes = ResumeService.resumeFilter(null, null, null, null, null, null, null, null, null);
        Assertions.assertEquals(2, resumes.size());

        resumes = ResumeService.resumeFilter(desiredPositions,  null, 10000L, 1000000L, Positions, null, institutes, faculties, cities);
        Assertions.assertEquals(1, resumes.size());

        resumes = ResumeService.resumeFilter(desiredPositions, null, 10000L, 1000000L, null, null, null, null, null);
        Assertions.assertEquals(2, resumes.size());

        resumes = ResumeService.resumeFilter(null, Companies, null, null,  null, null, null, faculties, null);
        Assertions.assertEquals(1, resumes.size());

        cities = new ArrayList<>();
        cities.add(CityService.findById(7));
        resumes = ResumeService.resumeFilter(desiredPositions, null, 10000L, 1000000L,  Positions, edLevels, institutes, faculties, cities);
        Assertions.assertEquals(0, resumes.size());

        faculties = new ArrayList<>();
        faculties.add("ВМиК");
        resumes = ResumeService.resumeFilter(desiredPositions, null, 10000L, 1000000L,  Positions, edLevels, institutes, faculties, null);
        Assertions.assertEquals(0, resumes.size());
    }
}
