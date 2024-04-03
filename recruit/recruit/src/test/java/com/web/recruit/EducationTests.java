package com.web.recruit;

import org.junit.jupiter.api.Test;
import com.web.recruit.models.*;
import com.web.recruit.services.*;
import org.junit.jupiter.api.*;

import java.sql.Date;
import java.util.List;


public class EducationTests {

    private final ApplicantService applicantService = new ApplicantService();
    private final EdlevelService edlevelService = new EdlevelService();

    private final Applicant applicant = applicantService.findById(1);
    private final Edlevel edlevel = edlevelService.findById(1);

    @Test
    public void testEducation() {
        String institute = "МГУ им М.В. Ломоносова";
        String faculty = "ВМК";
        java.sql.Date date = Date.valueOf("2020-09-01");

        Education education = new Education(applicant, edlevel, institute, faculty, date);
        Assertions.assertEquals(education.getApplicant(), applicant);
        Assertions.assertEquals(education.getEdlevel(), edlevel);
        Assertions.assertEquals(education.getInstitute(), institute);
        Assertions.assertEquals(education.getFaculty(), faculty);
        Assertions.assertEquals(education.getBeginDate(), date);
    }

    @Test
    public void testFindById() {
        EducationService educationService = new EducationService();
        Education education = educationService.findById(1L);
        Assertions.assertEquals(education.getEducationId(), 1L);
    }

    @Test
    public void testFindAll() {
        EducationService educationService = new EducationService();
        List<Education> Education = educationService.findAll();
        Assertions.assertEquals(Education.size(), 2);

        Education cur_education = educationService.findById(1);
        Applicant cur_applicant = cur_education.getApplicant();
        Edlevel cur_edlevel = cur_education.getEdlevel();
        String cur_institute = cur_education.getInstitute();
        String cur_faculty = cur_education.getFaculty();
        java.sql.Date cur_beginDate = cur_education.getBeginDate();
        java.sql.Date cur_endDate = cur_education.getEndDate();
        Assertions.assertEquals(Education.get(0).getApplicant(), cur_applicant);
        Assertions.assertEquals(Education.get(0).getEdlevel(), cur_edlevel);
        Assertions.assertEquals(Education.get(0).getInstitute(), cur_institute);
        Assertions.assertEquals(Education.get(0).getFaculty(), cur_faculty);
        Assertions.assertEquals(Education.get(0).getBeginDate(), cur_beginDate);
        Assertions.assertEquals(Education.get(0).getEndDate(), cur_endDate);

        cur_education = educationService.findById(2);
        cur_applicant = cur_education.getApplicant();
        cur_edlevel = cur_education.getEdlevel();
        cur_institute = cur_education.getInstitute();
        cur_faculty = cur_education.getFaculty();
        cur_beginDate = cur_education.getBeginDate();
        cur_endDate = cur_education.getEndDate();
        Assertions.assertEquals(Education.get(1).getApplicant(), cur_applicant);
        Assertions.assertEquals(Education.get(1).getEdlevel(), cur_edlevel);
        Assertions.assertEquals(Education.get(1).getInstitute(), cur_institute);
        Assertions.assertEquals(Education.get(1).getFaculty(), cur_faculty);
        Assertions.assertEquals(Education.get(1).getBeginDate(), cur_beginDate);
        Assertions.assertEquals(Education.get(1).getEndDate(), cur_endDate);
   }

    @Test
    public void testDeleteById() {
        String institute = "МПГУ";
        String faculty = "КМВ";
        java.sql.Date date = Date.valueOf("2020-09-01");

        Education tmp_education = new Education(applicant, edlevel, institute, faculty, date);
        EducationService EducationService = new EducationService();
        EducationService.save(tmp_education);
        Education checK_education = EducationService.findById(tmp_education.getEducationId());
        Assertions.assertEquals(tmp_education, checK_education);

        EducationService.deleteById(tmp_education.getEducationId());
        checK_education = EducationService.findById(tmp_education.getEducationId());
        Assertions.assertNull(checK_education);
    }
}
