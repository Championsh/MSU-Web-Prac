package com.web.recruit.models;

import java.io.Serializable;
import java.util.Objects;

public class ExperienceResumeId implements Serializable {

    private Resume resume;

    private Experience experience;

    public ExperienceResumeId() {
    }

    public ExperienceResumeId(Resume resume, Experience experience) {
        this.resume = resume;
        this.experience = experience;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExperienceResumeId)) return false;
        ExperienceResumeId that = (ExperienceResumeId) o;
        return Objects.equals(getResume(), that.getResume()) &&
                Objects.equals(getExperience(), that.getExperience());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getResume(), getExperience());
    }

    @Override
    public String toString() {
        return "ExperienceResumeId{" +
                "resume=" + resume +
                ", experience=" + experience +
                '}';
    }
}