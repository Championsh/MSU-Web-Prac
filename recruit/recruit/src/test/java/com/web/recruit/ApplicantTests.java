package com.web.recruit;

import org.junit.jupiter.api.Test;
import com.web.recruit.models.*;
import com.web.recruit.services.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.*;


public class ApplicantTests {
    private final AuthService authService = new AuthService();
    private final CityService cityService = new CityService();

    private final Auth auth = authService.findById(1);
    private final City city = cityService.findById(1);

    @Test
    public void testApplicant() {
        String fullname = "Ревва Тимур Дмитриевич";
        String phonenumber = "89391689376";
        Integer age = 40;
        Boolean status = true;

        Applicant applicant = new Applicant(auth, fullname, phonenumber, age, city, status);
        Assertions.assertEquals(applicant.getAuth(), auth);
        Assertions.assertEquals(applicant.getFullname(), fullname);
        Assertions.assertEquals(applicant.getContactInfo(), phonenumber);
        Assertions.assertEquals(applicant.getCity(), city);
        Assertions.assertEquals(applicant.getStatus(), status);
        Assertions.assertEquals(applicant.getAge(), age);
    }

    @Test
    public void testFindById() {
        ApplicantService applicantService = new ApplicantService();
        Applicant applicant = applicantService.findById(1L);
        Assertions.assertEquals(applicant.getApplicantId(), 1L);
    }

    @Test
    public void testFindAll() {
        ApplicantService applicantService = new ApplicantService();
        List<Applicant> applicant = applicantService.findAll();
        Assertions.assertEquals(applicant.size(), 2);
        Assertions.assertEquals(applicant.get(0).getApplicantId(), 1L);
        Assertions.assertEquals(applicant.get(1).getApplicantId(), 2L);
    }

    @Test
    public void testDeleteById() {
        String fullname = "Пломбиров Шоколад Иванович";
        String phonenumber = "89858888898";
        Integer age = 30;
        Boolean status = false;
        Auth auth = authService.findById(3);

        ApplicantService applicantService = new ApplicantService();
        Applicant tmp_applicant = new Applicant(auth, fullname, phonenumber, age, city, status);
        applicantService.save(tmp_applicant);
        Applicant checK_applicant = applicantService.findById(tmp_applicant.getApplicantId());
        Assertions.assertEquals(tmp_applicant, checK_applicant);

        applicantService.delete(tmp_applicant);
        checK_applicant = applicantService.findById(tmp_applicant.getApplicantId());
        Assertions.assertNull(checK_applicant);
    }

    @Test
    public void testFindEducations() {
        ApplicantService applicantService = new ApplicantService();
        Applicant applicant = applicantService.findById(1);
        List<Education> educations = applicantService.findEducations(applicant);
        Assertions.assertEquals(educations.size(), 1);
        Assertions.assertEquals(educations.getFirst().getEducationId(), 1);
        Assertions.assertEquals(educations.getFirst().getApplicant().getApplicantId(), 1);
        Assertions.assertEquals(educations.getFirst().getEdlevel().getEdlevelId(), 1);
        Assertions.assertEquals(educations.getFirst().getInstitute(), "МГУ им М.В. Ломоносова");
        Assertions.assertEquals(educations.getFirst().getFaculty(), "ВМК");
        Assertions.assertEquals(educations.getFirst().getBeginDate(), Date.valueOf("2000-09-01"));
        Assertions.assertEquals(educations.getFirst().getEndDate(), Date.valueOf("2004-06-20"));
    }

    @Test
    public void testFindExperiences() {
        ApplicantService applicantService = new ApplicantService();
        Applicant applicant = applicantService.findById(2);
        List<Experience> experiences = applicantService.findExperiences(applicant);
        Assertions.assertEquals(experiences.size(), 1);
        Assertions.assertEquals(experiences.getFirst().getExperienceId(), 2);
        Assertions.assertEquals(experiences.getFirst().getCompany(), "ОАО ИТ Компания");
        Assertions.assertEquals(experiences.getFirst().getPosition(), "Джуниор Java разработчик");
        Assertions.assertEquals(experiences.getFirst().getBeginDate(), Date.valueOf("2020-07-10"));
        Assertions.assertEquals(experiences.getFirst().getEndDate(), Date.valueOf("2024-02-21"));
        Assertions.assertEquals(experiences.getFirst().getSalary(), 150000);
    }

    @Test
    public void testFindResumes() {
        ApplicantService applicantService = new ApplicantService();
        Applicant applicant = applicantService.findById(1);
        List<Resume> resumes = applicantService.findResumes(applicant);
        Assertions.assertEquals(resumes.size(), 1);
        Assertions.assertEquals(resumes.getFirst().getResumeId(), 1);
        Assertions.assertEquals(resumes.getFirst().getApplicant().getApplicantId(), 1);
        Assertions.assertEquals(resumes.getFirst().getDesiredPosition(), "Директор по развитию");
        Assertions.assertEquals(resumes.getFirst().getDesiredSalary(), 1000000);
        Assertions.assertEquals(resumes.getFirst().getExperienceResumes().size(), 1);
        Assertions.assertEquals(resumes.getFirst().getExperienceResumes().get(0).getResume().getResumeId(), 1);
        Assertions.assertEquals(resumes.getFirst().getExperienceResumes().get(0).getExperience().getExperienceId(), 1);
        Assertions.assertEquals(resumes.getFirst().getExperienceResumes().get(0).getAdditionalInfo(), "Продвинутое владение Word");
    }

    @Test
    public void testFindCompanies() {
        ApplicantService applicantService = new ApplicantService();
        Applicant applicant = applicantService.findById(1);
        Set<Company> companies = applicantService.findCompanies(applicant);
        Assertions.assertEquals(companies.size(), 1);
        for (Company company: companies) {
            Assertions.assertEquals(company.getCompanyId(), 1);
            Assertions.assertEquals(company.getAuth().getAuthId(), 3);
            Assertions.assertEquals(company.getCompanyName(), "ПАО Сбербанк");
            Assertions.assertEquals(company.getVacancies().size(), 1);
            Assertions.assertEquals(company.getVacancies().getFirst().getVacancyId(), 1);
            Assertions.assertEquals(company.getApplicants().size(), 1);
        }
   }
}
