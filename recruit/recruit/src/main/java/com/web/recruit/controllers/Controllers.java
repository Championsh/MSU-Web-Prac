package com.web.recruit.controllers;

import com.web.recruit.models.Applicant;
import com.web.recruit.models.Auth;
import com.web.recruit.models.AuthRole;
import com.web.recruit.models.Company;
import com.web.recruit.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Objects;

@Controller
public class Controllers {
    Auth loginAuth = null;
    String loginMail = null;
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
    public String index(Model model) {
        if (!Objects.isNull(loginMail)) {
            model.addAttribute("loginMail", loginMail);
        }
        return "index";
    }

    @GetMapping("/navbar")
    public String navbar(Model model) {
        if (!Objects.isNull(loginMail)) {
            model.addAttribute("loginMail", loginMail);
        }
        return "navbar";
    }

    @GetMapping("/agreement")
    public String agreement() {
        return "agreement";
    }

    @GetMapping("/important")
    public String important() {
        return "important";
    }

    @GetMapping("/select")
    public String select() {
        return "select";
    }

    @GetMapping("/login")
    public String processLogin(@RequestParam String mail,
                               @RequestParam String password) {
        if (Objects.isNull(loginAuth)) {
            loginAuth = aus.validateAuth(mail, password);
            if (Objects.isNull(loginAuth)) {
                return "redirect:/index?showLoginModal=true";
            } else {
                loginMail = mail;
                return "redirect:/post";
            }
        }
        return "redirect:/post";
    }

//    @GetMapping("/register")
//    public String processRegistration(@RequestParam String mail,
//                                      @RequestParam String password,
//                                      @RequestParam String role,
//                                      Model model) {
//        model.addAttribute("selectedClientVAC", clientName);
//        model.addAttribute("selectedServiceVAC", serviceName);
//        model.addAttribute("selectedContractVAC", contract);
//
//        Auth client = cs.findByName(clientName);
//        Service service = ss.findByName(serviceName);
//
//        if (Objects.isNull(client) || Objects.isNull(service)) {
//            model.addAttribute("findError", true);
//        } else {
//            Client2Service client2Service = new Client2Service(contract, client, service, new java.util.Date(), null);
//            try {
//                css.save(client2Service);
//                model.addAttribute("findError", false);
//            } catch (Exception e) {
//                model.addAttribute("findError", true);
//            }
//        }
//        return "register_contract";
//
//    }

    @GetMapping("/post")
    public String post(Model model) {
        if (Objects.isNull(loginAuth)) {
            return "redirect:/index?showLoginModal=true";
        }

        if (Objects.equals(loginAuth.getAuthRole(), AuthRole.valueOf("соискатель")) ||
                (Objects.equals(loginAuth.getAuthRole(), AuthRole.valueOf("студент")))) {
            try {
                Applicant applicant = as.findByAuth(loginAuth);
                model.addAttribute("applicant", applicant);
                model.addAttribute("company", null);
                model.addAttribute("experiences", as.findExperiences(applicant));
                model.addAttribute("educations", as.findEducations(applicant));
                model.addAttribute("resumes", as.findResumes(applicant));
                model.addAttribute("companies", as.findCompanies(applicant));
                model.addAttribute("findError", false);
            } catch (Exception e) {
                model.addAttribute("findError", true);
            }
        } else {
            try {
                Company company = cs.findByAuth(loginAuth);
                model.addAttribute("applicant", null);
                model.addAttribute("company", company);
                model.addAttribute("vacancies", cs.findVacancies(company));
                model.addAttribute("applicantcompanies", cs.findApplicantCompanies(company));
                model.addAttribute("findError", false);
            } catch (Exception e) {
                model.addAttribute("findError", true);
            }
        }
        return "post";
    }

    @GetMapping("/resume")
    public String resume(@RequestParam(value = "resumeId") Long resumeId,
                         @RequestParam(name = "deleteResumeBtn", required = false) String deleteResumeBtn,
                         @RequestParam(name = "changeResumeBtn", required = false) String changeResumeBtn,
                         @RequestParam(name = "findVacanciesBtn", required = false) String findVacanciesBtn) {
        if (deleteResumeBtn != null) {
            return "redirect:/";
        } else if (changeResumeBtn != null) {
            return "redirect:/";
        } else {
            return "redirect:/select?resumeId=" + resumeId;
        }
    }

    @GetMapping("/addResume")
    public String addResume() {
        return "redirect:/addEntity";
    }

    @GetMapping("/addApplicant")
    public String addApplicant() {
        return "redirect:/addEntity";
    }

    @GetMapping("/addEducation")
    public String addEducation() {
        return "redirect:/addEntity";
    }

    @GetMapping("/addExperience")
    public String addExperience() {
        return "redirect:/addEntity";
    }

    @GetMapping("/addVacancy")
    public String addVacancy() {
        return "redirect:/addEntity";
    }

    @GetMapping("/addEntity")
    public String addEntity() {
        return "addEntity";
    }
}