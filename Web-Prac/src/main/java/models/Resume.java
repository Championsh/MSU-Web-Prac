package models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

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


    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ExperienceResume> experienceResumes = new ArrayList<>();

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<EducationResume> educationResumes = new ArrayList<>();


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
