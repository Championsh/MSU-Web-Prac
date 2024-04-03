package com.web.recruit;

import org.junit.jupiter.api.Test;
import com.web.recruit.models.*;
import com.web.recruit.services.*;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.*;


public class CompanyTests {
    private final AuthService authService = new AuthService();
    private final Auth auth = authService.findById(3);

    @Test
    public void testCompany() {
        String companyName = "ПАО Сбербанк";

        Company company = new Company(auth, companyName);
        Assertions.assertEquals(company.getAuth(), auth);
        Assertions.assertEquals(company.getCompanyName(), companyName);
    }

    @Test
    public void testFindById() {
        CompanyService companyService = new CompanyService();
        Company company = companyService.findById(1L);
        Assertions.assertEquals(company.getCompanyId(), 1L);
    }

    @Test
    public void testFindAll() {
        CompanyService companyService = new CompanyService();
        List<Company> company = companyService.findAll();
        Assertions.assertEquals(company.size(), 2);
        Assertions.assertEquals(company.get(0).getCompanyId(), 1L);
        Assertions.assertEquals(company.get(1).getCompanyId(), 2L);
    }

    @Test
    public void testDeleteById() {
        String companyName = "ООО ВМиК";
        Auth auth = new Auth("fifth@mail.ru", "fifthpwd", "работодатель");

        CompanyService companyService = new CompanyService();
        Company tmp_company = new Company(auth, companyName);
        companyService.save(tmp_company);
        Company checK_company = companyService.findById(tmp_company.getCompanyId());
        Assertions.assertEquals(tmp_company, checK_company);

        companyService.deleteById(tmp_company.getCompanyId());
        checK_company = companyService.findById(tmp_company.getCompanyId());
        Assertions.assertNull(checK_company);
    }

    @Test
    public void testFindVacancies() {
        CompanyService companyService = new CompanyService();
        Company company = companyService.findById(1);
        List<Vacancy> vacancies = companyService.findVacancies(company);
        Assertions.assertEquals(vacancies.size(), 1);
        Assertions.assertEquals(vacancies.getFirst().getVacancyId(), 1);
        Assertions.assertEquals(vacancies.getFirst().getVacancyName(), "Директор по развитию");
        Assertions.assertEquals(vacancies.getFirst().getSalary(), 1000000);
        Assertions.assertEquals(vacancies.getFirst().getRequirements(), "Продвинутое владение Word");
    }

    @Test
    public void testFindApplicants() {
        CompanyService companyService = new CompanyService();
        Company company = companyService.findById(1);
        Set<Applicant> applicants = companyService.findApplicants(company);
        Assertions.assertEquals(applicants.size(), 1);
        for (Applicant applicant: applicants) {
            Assertions.assertEquals(applicant.getApplicantId(), 1);
            Assertions.assertEquals(applicant.getAuth().getAuthId(), 1);
        }
    }
}
