package com.web.recruit.models;

import java.io.Serializable;
import java.util.Objects;

public class EducationResumeId implements Serializable {

    private Resume resume;

    private Education education;

    public EducationResumeId() {
    }

    public EducationResumeId(Resume resume, Education education) {
        this.resume = resume;
        this.education = education;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EducationResumeId)) return false;
        EducationResumeId that = (EducationResumeId) o;
        return Objects.equals(getResume(), that.getResume()) &&
                Objects.equals(getEducation(), that.getEducation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getResume(), getEducation());
    }

    @Override
    public String toString() {
        return "EducationResumeId{" +
                "resume=" + resume +
                ", education=" + education +
                '}';
    }
}