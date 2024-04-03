package com.web.recruit;

import org.junit.jupiter.api.Test;
import com.web.recruit.models.*;
import com.web.recruit.services.*;
import org.junit.jupiter.api.*;

import java.sql.Date;
import java.util.List;


public class ExperienceTests {

    private final ApplicantService applicantService = new ApplicantService();
    private final Applicant applicant = applicantService.findById(1);

    @Test
    public void testExperience() {
        String company = "ООО ВМиК";
        String position = "Старший программист";
        java.sql.Date date = Date.valueOf("2020-09-01");
        Long salary = 350000L;

        Experience experience = new Experience(applicant, company, position, date, salary);
        Assertions.assertEquals(experience.getApplicant(), applicant);
        Assertions.assertEquals(experience.getCompany(), company);
        Assertions.assertEquals(experience.getPosition(), position);
        Assertions.assertEquals(experience.getBeginDate(), date);
        Assertions.assertEquals(experience.getSalary(), salary);
    }

    @Test
    public void testFindById() {
        ExperienceService experienceService = new ExperienceService();
        Experience experience = experienceService.findById(1L);
        Assertions.assertEquals(experience.getExperienceId(), 1L);
    }

    @Test
    public void testFindAll() {
        ExperienceService experienceService = new ExperienceService();
        List<Experience> Experience = experienceService.findAll();
        Assertions.assertEquals(Experience.size(), 3);

        for (int i = 0; i < Experience.size(); i++) {
            Experience cur_experience = experienceService.findById(i + 1);
            Applicant cur_applicant = cur_experience.getApplicant();
            String cur_company = cur_experience.getCompany();
            String cur_position = cur_experience.getPosition();
            Long cur_salary = cur_experience.getSalary();
            java.sql.Date cur_beginDate = cur_experience.getBeginDate();
            java.sql.Date cur_endDate = cur_experience.getEndDate();
            Assertions.assertEquals(Experience.get(i).getApplicant(), cur_applicant);
            Assertions.assertEquals(Experience.get(i).getCompany(), cur_company);
            Assertions.assertEquals(Experience.get(i).getPosition(), cur_position);
            Assertions.assertEquals(Experience.get(i).getSalary(), cur_salary);
            Assertions.assertEquals(Experience.get(i).getBeginDate(), cur_beginDate);
            Assertions.assertEquals(Experience.get(i).getEndDate(), cur_endDate);
        }
    }

    @Test
    public void testDeleteById() {
        String company = "ООО ВМиК";
        String position = "Старший программист";
        java.sql.Date date = Date.valueOf("2020-09-01");
        Long salary = 350000L;

        Experience tmp_experience = new Experience(applicant, company, position, date, salary);
        ExperienceService ExperienceService = new ExperienceService();
        ExperienceService.save(tmp_experience);
        Experience checK_experience = ExperienceService.findById(tmp_experience.getExperienceId());
        Assertions.assertEquals(tmp_experience, checK_experience);

        ExperienceService.deleteById(tmp_experience.getExperienceId());
        checK_experience = ExperienceService.findById(tmp_experience.getExperienceId());
        Assertions.assertNull(checK_experience);
    }
}
