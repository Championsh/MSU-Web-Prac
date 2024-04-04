package com.web.recruit;

import org.junit.jupiter.api.Test;
import com.web.recruit.models.*;
import com.web.recruit.services.*;
import org.junit.jupiter.api.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class VacancyTests {

    private final CompanyService companyService = new CompanyService();
    private final Company company = companyService.findById(1);

    @Test
    public void testVacancy() {
        String vacancyName = "Директор по маркетингу";
        String requirements = "Продвинутое владение Word";
        Long salary = 550000L;

        Vacancy vacancy = new Vacancy(company, vacancyName, salary, requirements);
        Assertions.assertEquals(vacancy.getCompany(), company);
        Assertions.assertEquals(vacancy.getVacancyName(), vacancyName);
        Assertions.assertEquals(vacancy.getSalary(), salary);
        Assertions.assertEquals(vacancy.getRequirements(), requirements);
    }

    @Test
    public void testFindById() {
        VacancyService vacancyService = new VacancyService();
        Vacancy vacancy = vacancyService.findById(1);
        Assertions.assertEquals(vacancy.getVacancyId(), 1);
    }

    @Test
    public void testFindAll() {
        VacancyService vacancyService = new VacancyService();
        List<Vacancy> Vacancy = vacancyService.findAll();
        Assertions.assertEquals(Vacancy.size(), 2);

        for (int i = 0; i < Vacancy.size(); i++) {
            Vacancy cur_vacancy = vacancyService.findById(i + 1);
            Company cur_company = cur_vacancy.getCompany();
            String cur_position = cur_vacancy.getVacancyName();
            Long cur_salary = cur_vacancy.getSalary();
            String cur_requirements = cur_vacancy.getRequirements();

            Assertions.assertEquals(Vacancy.get(i).getCompany(), cur_company);
            Assertions.assertEquals(Vacancy.get(i).getVacancyName(), cur_position);
            Assertions.assertEquals(Vacancy.get(i).getSalary(), cur_salary);
            Assertions.assertEquals(Vacancy.get(i).getRequirements(), cur_requirements);
        }
    }

    @Test
    public void testDeleteById() {
        String vacancyName = "Директор по маркетингу";
        String requirements = "Продвинутое владение Word";
        Long salary = 550000L;

        Vacancy tmp_vacancy = new Vacancy(company, vacancyName, salary, requirements);
        VacancyService VacancyService = new VacancyService();
        VacancyService.save(tmp_vacancy);
        Vacancy checK_vacancy = VacancyService.findById(tmp_vacancy.getVacancyId());
        Assertions.assertEquals(tmp_vacancy, checK_vacancy);

        VacancyService.deleteById(tmp_vacancy.getVacancyId());
        checK_vacancy = VacancyService.findById(tmp_vacancy.getVacancyId());
        Assertions.assertNull(checK_vacancy);
    }

    @Test
    public void testFindVacancyResumes() {
        VacancyService vacancyService = new VacancyService();
        Vacancy vacancy = vacancyService.findById(2);
        List<Resume> findVacancyResumes = vacancyService.findVacancyResumes(vacancy);

        Assertions.assertEquals(1, findVacancyResumes.size());
        Assertions.assertEquals(2, findVacancyResumes.getFirst().getResumeId());

        String vacancyName = "Директор по развитию";
        String requirements = "Продвинутое владение Word; Желание рисовать плакаты";
        Long salary = 5500000L;

        Vacancy tmp_vacancy = new Vacancy(company, vacancyName, salary, requirements);
        VacancyService VacancyService = new VacancyService();
        VacancyService.save(tmp_vacancy);
        Vacancy checK_vacancy = VacancyService.findById(tmp_vacancy.getVacancyId());
        Assertions.assertEquals(tmp_vacancy, checK_vacancy);

        findVacancyResumes = vacancyService.findVacancyResumes(tmp_vacancy);
        Assertions.assertEquals(0, findVacancyResumes.size());

        VacancyService.deleteById(tmp_vacancy.getVacancyId());
        checK_vacancy = VacancyService.findById(tmp_vacancy.getVacancyId());
        Assertions.assertNull(checK_vacancy);
    }

    @Test
    public void testVacancyFilter() {
        VacancyService VacancyService = new VacancyService();
        CompanyService CompanyService = new CompanyService();

        List<Company> Companies = new ArrayList<>();
        Companies.add(CompanyService.findById(1));
        Companies.add(CompanyService.findById(2));

        List<String> vacancyNames = new ArrayList<>();
        vacancyNames.add("Директор по развитию");

        List<Vacancy> vacancies = VacancyService.vacancyFilter(null, null, null, null);
        Assertions.assertEquals(vacancies.size(), 2);

        vacancies = VacancyService.vacancyFilter(vacancyNames, Companies, 10000L, 1000000L);
        Assertions.assertEquals(vacancies.size(), 1);
    }
}
