package com.web.recruit.models;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "applicant", schema = "web")
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicant_id")
    private Long applicantId;

    @ManyToOne(targetEntity = Auth.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "auth_id")
    private Auth auth;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "contact_info")
    private String contactInfo;

    @Column(name = "age")
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "status")
    private Boolean status;


    @OneToMany(mappedBy = "applicant", fetch = FetchType.EAGER, cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Experience> experiences = new ArrayList<>();

    @OneToMany(mappedBy = "applicant", fetch = FetchType.EAGER, cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Education> educations = new ArrayList<>();

    @OneToMany(mappedBy = "applicant", fetch = FetchType.EAGER, cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Resume> resumes = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "applicant_company",
            joinColumns = { @JoinColumn(name = "company_id") },
            inverseJoinColumns = { @JoinColumn(name = "applicant_id") }
    )
    Set<Company> companies = new HashSet<>();

    public Applicant() {
    }

    public Applicant(Auth auth, String fullname, String contactInfo, Integer age, City city) {
        this.auth = auth;
        this.fullname = fullname;
        this.contactInfo = contactInfo;
        this.age = age;
        this.city = city;
    }

    public Applicant(Auth auth, String fullname, String contactInfo, Integer age, City city, Boolean status) {
        this.auth = auth;
        this.fullname = fullname;
        this.contactInfo = contactInfo;
        this.age = age;
        this.city = city;
        this.status = status;
    }


    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public List<Resume> getResumes() {
        return resumes;
    }

    public void setResumes(List<Resume> resumes) {
        this.resumes = resumes;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Applicant)) return false;
        Applicant applicant = (Applicant) o;
        return Objects.equals(getApplicantId(), applicant.getApplicantId()) &&
                Objects.equals(getAuth(), applicant.getAuth()) &&
                Objects.equals(getFullname(), applicant.getFullname()) &&
                Objects.equals(getContactInfo(), applicant.getContactInfo()) &&
                Objects.equals(getAge(), applicant.getAge()) &&
                Objects.equals(getCity(), applicant.getCity()) &&
                Objects.equals(getStatus(), applicant.getStatus());
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "applicantId=" + applicantId +
                ", auth=" + auth +
                ", fullname='" + fullname + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", age=" + age +
                ", city=" + city +
                ", status=" + status +
                '}';
    }
}
