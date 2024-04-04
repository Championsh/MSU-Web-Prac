package com.web.recruit.models;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "education", schema = "web")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "education_id")
    private Long educationId;

    @ManyToOne(targetEntity = Applicant.class)
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @ManyToOne(targetEntity = Edlevel.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "edlevel_id")
    private Edlevel edlevel;

    @Column(name = "institute")
    private String institute;

    @Column(name = "faculty")
    private String faculty;

    @Column(name = "begin_date")
    private java.sql.Date beginDate;

    @Column(name = "end_date")
    private java.sql.Date endDate;

    public Education() {
    }

    public Education(Applicant applicant, Edlevel edlevel, String institute, String faculty, java.sql.Date beginDate) {
        this.applicant = applicant;
        this.edlevel = edlevel;
        this.institute = institute;
        this.faculty = faculty;
        this.beginDate = beginDate;
        this.endDate = null;
    }

    public Education(Applicant applicant, Edlevel edlevel, String institute, String faculty, java.sql.Date beginDate, java.sql.Date endDate) {
        this.applicant = applicant;
        this.edlevel = edlevel;
        this.institute = institute;
        this.faculty = faculty;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public Long getEducationId() {
        return educationId;
    }

    public void setEducationId(Long educationId) {
        this.educationId = educationId;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public Edlevel getEdlevel() {
        return edlevel;
    }

    public void setEdlevel(Edlevel edlevel) {
        this.edlevel = edlevel;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Education)) return false;
        Education education = (Education) o;
        return Objects.equals(getEducationId(), education.getEducationId()) &&
                Objects.equals(getApplicant(), education.getApplicant()) &&
                Objects.equals(getEdlevel(), education.getEdlevel()) &&
                Objects.equals(getInstitute(), education.getInstitute()) &&
                Objects.equals(getFaculty(), education.getFaculty()) &&
                Objects.equals(getBeginDate(), education.getBeginDate()) &&
                Objects.equals(getEndDate(), education.getEndDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEducationId(), getApplicant(), getEdlevel(), getInstitute(), getFaculty(), getBeginDate(), getEndDate());
    }

    @Override
    public String toString() {
        return "Education{" +
                "educationId=" + educationId +
                ", applicant=" + applicant +
                ", edlevel=" + edlevel +
                ", institute='" + institute + '\'' +
                ", faculty='" + faculty + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                '}';
    }
}
