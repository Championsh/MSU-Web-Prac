package com.web.recruit.models;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "edlevel", schema = "web")
public class Edlevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "edlevel_id")
    private Long edlevelId;

    @Column(name = "edlevel_name")
    private String edlevelName;

    public Edlevel() {
    }

    public Edlevel(String edlevelName) {
        this.edlevelName = edlevelName;
    }

    public Long getEdlevelId() {
        return edlevelId;
    }

    public void setEdlevelId(Long edlevelId) {
        this.edlevelId = edlevelId;
    }

    public String getEdlevelName() {
        return edlevelName;
    }

    public void setEdlevelName(String edlevelName) {
        this.edlevelName = edlevelName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edlevel)) return false;
        Edlevel edlevel = (Edlevel) o;
        return Objects.equals(getEdlevelId(), edlevel.getEdlevelId()) &&
                Objects.equals(getEdlevelName(), edlevel.getEdlevelName());
    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getEdlevelId(), getEdlevelName());
//    }

    @Override
    public String toString() {
        return "Edlevel{" +
                "edlevelId=" + edlevelId +
                ", edlevelName='" + edlevelName + '\'' +
                '}';
    }
}
