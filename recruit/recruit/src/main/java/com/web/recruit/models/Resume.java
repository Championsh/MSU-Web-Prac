package com.web.recruit.models;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "resume", schema = "web")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_id")
    private Long resumeId;

    @ManyToOne(targetEntity = Applicant.class)
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @OneToMany(mappedBy = "resume", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExperienceResume> experienceResumes = new ArrayList<>();

    @OneToMany(mappedBy = "resume", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EducationResume> educationResumes = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "experience_resume",
            joinColumns = { @JoinColumn(name = "experience_id") },
            inverseJoinColumns = { @JoinColumn(name = "resume_id") }
    )
    Set<Experience> experiences = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "education_resume",
            joinColumns = { @JoinColumn(name = "education_id") },
            inverseJoinColumns = { @JoinColumn(name = "resume_id") }
    )
    Set<Education> educations = new HashSet<>();

    @Column(name = "desired_position")
    private String desiredPosition;

    @Column(name = "desired_salary")
    private Long desiredSalary;

    public Resume() {
    }

    public Resume(Applicant applicant, String desiredPosition, Long desiredSalary) {
        this.applicant = applicant;
        this.desiredPosition = desiredPosition;
        this.desiredSalary = desiredSalary;
    }


    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public String getDesiredPosition() {
        return desiredPosition;
    }

    public void setDesiredPosition(String desiredPosition) {
        this.desiredPosition = desiredPosition;
    }

    public Long getDesiredSalary() {
        return desiredSalary;
    }

    public void setDesiredSalary(Long desiredSalary) {
        this.desiredSalary = desiredSalary;
    }

    public List<ExperienceResume> getExperienceResumes() {
        return experienceResumes;
    }

    public void setExperienceResumes(List<ExperienceResume> experienceResumes) {
        this.experienceResumes = experienceResumes;
    }

    public List<EducationResume> getEducationResumes() {
        return educationResumes;
    }

    public void setEducationResumes(List<EducationResume> educationResumes) {
        this.educationResumes = educationResumes;
    }

    public Set<Education> getEducations() {
        return educations;
    }

    public void setEducations(Set<Education> educations) {
        this.educations = educations;
    }

    public Set<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(Set<Experience> experiences) {
        this.experiences = experiences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resume)) return false;
        Resume resume = (Resume) o;
        return Objects.equals(getResumeId(), resume.getResumeId()) &&
                Objects.equals(getApplicant(), resume.getApplicant()) &&
                Objects.equals(getDesiredPosition(), resume.getDesiredPosition()) &&
                Objects.equals(getDesiredSalary(), resume.getDesiredSalary());
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(getResumeId(), getApplicant(), getDesiredPosition(), getDesiredSalary(), getExperienceResumes(), getEducationResumes());
//    }

    @Override
    public String toString() {
        return "Resume{" +
                "resumeId=" + resumeId +
                ", applicant=" + applicant +
                ", desiredPosition='" + desiredPosition + '\'' +
                ", desiredSalary=" + desiredSalary +
                '}';
    }
}
