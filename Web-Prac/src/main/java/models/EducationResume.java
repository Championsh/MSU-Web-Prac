package models;

import jakarta.persistence.*;


@Entity
@Table(name = "education_resume", schema = "web")
public class EducationResume {
    @ManyToOne(targetEntity = Resume.class)
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @ManyToOne(targetEntity = Education.class)
    @JoinColumn(name = "education_id")
    private Education education;

    @Column(name = "additional_info")
    private String additionalInfo;

    public EducationResume() {
    }

    public EducationResume(Resume resume, Education education, String additionalInfo) {
        this.resume = resume;
        this.education = education;
        this.additionalInfo = additionalInfo;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
        return "EducationResume{" +
                "resume=" + resume +
                ", education=" + education +
                ", additionalInfo='" + additionalInfo + '\'' +
                '}';
    }
}
