package com.web.recruit.models;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "experience_resume", schema = "web")
@IdClass(ExperienceResumeId.class)
public class ExperienceResume {

    @Id

    @ManyToOne(targetEntity = Resume.class)
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @Id
    @ManyToOne(targetEntity = Experience.class)
    @JoinColumn(name = "experience_id")
    private Experience experience;

    @Column(name = "additional_info")
    private String additionalInfo;

    public ExperienceResume() {
    }

    public ExperienceResume(Resume resume, Experience experience) {
        this.resume = resume;
        this.experience = experience;
    }

    public ExperienceResume(Resume resume, Experience experience, String additionalInfo) {
        this.resume = resume;
        this.experience = experience;
        this.additionalInfo = additionalInfo;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
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
        result = mult * result + experience.hashCode();
        return mult * result + ((additionalInfo == null) ? 0 : additionalInfo.hashCode());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExperienceResume)) return false;
        ExperienceResume that = (ExperienceResume) o;
        return Objects.equals(getResume(), that.getResume()) &&
                Objects.equals(getExperience(), that.getExperience()) &&
                Objects.equals(getAdditionalInfo(), that.getAdditionalInfo());
    }

    @Override
    public String toString() {
        return "ExperienceResume{" +
                "resume='" + resume + '\'' +
                ", experience='" + experience + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                '}';
    }
}
