package models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "company", schema = "web")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long companyId;

    @ManyToOne(targetEntity = Auth.class)
    @JoinColumn(name = "auth_id")
    private Auth auth;

    @Column(name = "company_name")
    private String companyName;


    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vacancy> vacancies = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplicantCompany> applicantCompanies = new ArrayList<>();

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "applicant_company",
            joinColumns = { @JoinColumn(name = "company_id") },
            inverseJoinColumns = { @JoinColumn(name = "applicant_id") }
    )
    Set<Applicant> applicants = new HashSet<>();

    public Company() {
    }

    public Company(Auth auth, String companyName) {
        this.auth = auth;
        this.companyName = companyName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    public List<ApplicantCompany> getApplicantCompanies() {
        return applicantCompanies;
    }

    public void setApplicantCompanies(List<ApplicantCompany> applicantCompanies) {
        this.applicantCompanies = applicantCompanies;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", auth=" + auth +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
