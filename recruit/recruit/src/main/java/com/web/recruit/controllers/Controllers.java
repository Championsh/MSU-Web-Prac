package com.web.recruit.controllers;

import com.web.recruit.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class Controllers {
    ApplicantService as = new ApplicantService();
    AuthService aus = new AuthService();
    CityService cis = new CityService();
    CompanyService cs = new CompanyService();
    EdlevelService edls = new EdlevelService();
    EducationService es = new EducationService();
    ExperienceService exs = new ExperienceService();
    ResumeService rs = new ResumeService();
    VacancyService vs = new VacancyService();

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String processLogin(@RequestParam String mail,
                               @RequestParam String password,
                               Model model) {
        if (Objects.isNull(aus.validateAuth(mail, password))) {
            model.addAttribute("isBadTry", true);
            return "index";
        } else {
            model.addAttribute("isBadTry", false);
            return "redirect:/operations";
        }
    }

    @GetMapping("/operations")
    public String operations() {
        return "operations";
    }

}