package com.web.recruit.models;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;


public class ApplicantCompanyId implements Serializable {

    private Long applicantCompanyId;

    private Long companyApplicantId;

    public ApplicantCompanyId() {
    }

    public ApplicantCompanyId(Long applicantCompanyId, Long companyApplicantId) {
        this.applicantCompanyId = applicantCompanyId;
        this.companyApplicantId = companyApplicantId;
    }

    public Long getApplicantId() {
        return applicantCompanyId;
    }

    public Long getCompanyId() {
        return companyApplicantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApplicantCompanyId that)) return false;
        return Objects.equals(getApplicantId(), that.getApplicantId()) &&
                Objects.equals(getCompanyId(), that.getCompanyId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getApplicantId(), getCompanyId());
    }

    @Override
    public String toString() {
        return "ApplicantCompanyId{" +
                "applicant=" + applicantCompanyId +
                ", company=" + companyApplicantId +
                '}';
    }
}