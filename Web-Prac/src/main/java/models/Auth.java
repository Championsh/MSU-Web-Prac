package models;

import jakarta.persistence.*;


@Entity
@Table (name = "auth", schema = "web")
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_id")
    private Long authId;

    @Column(name = "mail")
    private String mail;

    @Column(name = "pwd")
    private String password;

    @Column(name = "auth_role")
    @Enumerated(EnumType.STRING)
    private RoleName authRole;

    public Auth() {
    }

    public Auth(String mail, String password, RoleName authRole) {
        this.mail = mail;
        this.password = password;
        this.authRole = authRole;
    }

    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleName getAuthRole() {
        return authRole;
    }

    public void setAuthRole(RoleName authRole) {
        this.authRole = authRole;
    }

    @Override
    public String toString() {
        return "models.Auth{" +
                "authId=" + authId +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", authRole='" + authRole + '\'' +
                '}';
    }
}