package models;

import jakarta.persistence.*;

@Entity
@Table(name = "applicant_company", schema = "web")
public class ApplicantCompany {
    @ManyToOne(targetEntity = Applicant.class)
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @ManyToOne(targetEntity = Company.class)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "begin_date")
    private java.sql.Date beginDate;

    @Column(name = "end_date")
    private java.sql.Date endDate;

    @Column(name = "position_name")
    private String positionName;

    @Column(name = "salary")
    private Long salary;

    public ApplicantCompany() {
    }

    public ApplicantCompany(Applicant applicant, Company company, java.sql.Date beginDate, java.sql.Date endDate, String positionName, Long salary) {
        this.applicant = applicant;
        this.company = company;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.positionName = positionName;
        this.salary = salary;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public java.sql.Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(java.sql.Date beginDate) {
        this.beginDate = beginDate;
    }

    public java.sql.Date getEndDate() {
        return endDate;
    }

    public void setEndDate(java.sql.Date endDate) {
        this.endDate = endDate;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ApplicantCompany{" +
                "applicant=" + applicant +
                ", company=" + company +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", positionName='" + positionName + '\'' +
                ", salary=" + salary +
                '}';
    }
}
