package models;

import jakarta.persistence.*;


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
    public String toString() {
        return "Edlevel{" +
                "edlevelId=" + edlevelId +
                ", edlevelName='" + edlevelName + '\'' +
                '}';
    }
}
