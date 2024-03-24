package models;

import jakarta.persistence.*;


@Entity
@Table(name = "experience_resume", schema = "web")
public class ExperienceResume {
    @ManyToOne(targetEntity = Resume.class)
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @ManyToOne(targetEntity = Experience.class)
    @JoinColumn(name = "experience_id")
    private Experience experience;

    @Column(name = "additional_info")
    private String additionalInfo;

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
    public String toString() {
        return "ExperienceResume{" +
                "resume=" + resume +
                ", experience=" + experience +
                ", additionalInfo='" + additionalInfo + '\'' +
                '}';
    }
}
