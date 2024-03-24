package models;

import jakarta.persistence.*;


@Entity
@Table(name = "vacancy", schema = "web")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacancy_id")
    private Long vacancyId;

    @ManyToOne(targetEntity = Company.class)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "vacancy_name")
    private String vacancyName;

    @Column(name = "salary")
    private Long salary;

    @Column(name = "requirements")
    private String requirements;

    @Column(name = "additional_info")
    private String additionalInfo;

    public Vacancy() {
    }

    public Vacancy(Company company, String vacancyName, Long salary, String requirements, String additionalInfo) {
        this.company = company;
        this.vacancyName = vacancyName;
        this.salary = salary;
        this.requirements = requirements;
        this.additionalInfo = additionalInfo;
    }

    public Long getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(Long vacancyId) {
        this.vacancyId = vacancyId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getVacancyName() {
        return vacancyName;
    }

    public void setVacancyName(String vacancyName) {
        this.vacancyName = vacancyName;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "vacancyId=" + vacancyId +
                ", company=" + company +
                ", vacancyName='" + vacancyName + '\'' +
                ", salary=" + salary +
                ", requirements='" + requirements + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                '}';
    }
}
