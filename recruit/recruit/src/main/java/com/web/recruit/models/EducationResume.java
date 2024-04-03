package com.web.recruit.models;
import jakarta.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "education_resume", schema = "web")
@IdClass(EducationResumeId.class)
public class EducationResume {

    @Id
    @ManyToOne(targetEntity = Resume.class)
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @Id
    @ManyToOne(targetEntity = Education.class)
    @JoinColumn(name = "education_id")
    private Education education;

    @Column(name = "additional_info")
    private String additionalInfo;

    public EducationResume() {
    }

    public EducationResume(Resume resume, Education education) {
        this.resume = resume;
        this.education = education;
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
    public int hashCode() {
        final int mult = 17;
        int result = 1;
        result = mult * result + resume.hashCode();
        result = mult * result + education.hashCode();
        return mult * result + ((additionalInfo == null) ? 0 : additionalInfo.hashCode());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EducationResume)) return false;
        EducationResume that = (EducationResume) o;
        return Objects.equals(getResume(), that.getResume()) &&
                Objects.equals(getEducation(), that.getEducation()) &&
                Objects.equals(getAdditionalInfo(), that.getAdditionalInfo());
    }

    @Override
    public String toString() {
        return "EducationResume{" +
                "resume='" + resume + '\'' +
                ", education='" + education + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                '}';
    }
}
