package com.web.recruit.models;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "experience", schema = "web")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "experience_id")
    private Long experienceId;

    @ManyToOne(targetEntity = Applicant.class)
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @Column(name = "company")
    private String company;

    @Column(name = "position")
    private String position;

    @Column(name = "begin_date")
    private java.sql.Date beginDate;

    @Column(name = "end_date")
    private java.sql.Date endDate;

    @Column(name = "salary")
    private Long salary;

    public Experience() {
    }

    public Experience(Applicant applicant, String company, String position, java.sql.Date beginDate, Long salary) {
        this.applicant = applicant;
        this.company = company;
        this.position = position;
        this.beginDate = beginDate;
        this.endDate = null;
        this.salary = salary;
    }

    public Experience(Applicant applicant, String company, String position, java.sql.Date beginDate, java.sql.Date endDate, Long salary) {
        this.applicant = applicant;
        this.company = company;
        this.position = position;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.salary = salary;
    }

    public Long getExperienceId() {
        return experienceId;
    }

    public void setExperienceId(Long experienceId) {
        this.experienceId = experienceId;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Experience)) return false;
        Experience experience = (Experience) o;
        return Objects.equals(getExperienceId(), experience.getExperienceId()) &&
                Objects.equals(getApplicant(), experience.getApplicant()) &&
                Objects.equals(getCompany(), experience.getCompany()) &&
                Objects.equals(getPosition(), experience.getPosition()) &&
                Objects.equals(getBeginDate(), experience.getBeginDate()) &&
                Objects.equals(getEndDate(), experience.getEndDate()) &&
                Objects.equals(getSalary(), experience.getSalary());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExperienceId(), getApplicant(), getCompany(), getPosition(), getBeginDate(), getEndDate(), getSalary());
    }

    @Override
    public String toString() {
        return "Experience{" +
                "experienceId=" + experienceId +
                ", applicant=" + applicant +
                ", company='" + company + '\'' +
                ", position='" + position + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", salary=" + salary +
                '}';
    }
}
