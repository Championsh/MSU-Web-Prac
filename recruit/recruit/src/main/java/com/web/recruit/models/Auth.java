package com.web.recruit.models;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.util.Objects;


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

//    @Convert(converter = AuthRoleConverter.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "auth_role")
//    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private AuthRole authRole;

    public Auth() {
    }

    public Auth(String mail, String password, String authRole) {
        this.mail = mail;
        this.password = password;
        this.authRole = AuthRole.valueOf(authRole.toLowerCase());
    }

    public Auth(String mail, String password, AuthRole authRole) {
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

    @Enumerated(EnumType.STRING)
    public AuthRole getAuthRole() {
        return authRole;
    }

    public void setAuthRole(AuthRole authRole) {
        this.authRole = authRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Auth)) return false;
        Auth auth = (Auth) o;
        return Objects.equals(getAuthId(), auth.getAuthId()) &&
                Objects.equals(getMail(), auth.getMail()) &&
                Objects.equals(getPassword(), auth.getPassword()) &&
                getAuthRole() == auth.getAuthRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthId(), getMail(), getPassword(), getAuthRole());
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