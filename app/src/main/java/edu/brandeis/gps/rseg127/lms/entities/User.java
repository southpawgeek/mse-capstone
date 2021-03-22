package edu.brandeis.gps.rseg127.lms.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="username")
    private String username;

    @Column(name = "user_type")
    private String userType;

    @Column(name="password_hash")
    @Setter(AccessLevel.NONE)
    private String passwordHash;

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = "{noop}" +passwordHash;
    }

    @Column(name="patron_id")
    private String patronId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="middle_name")
    private String middleName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="home_address")
    private String homeAddress;

    @Column(name="mail_address")
    private String mailAddress;

    @Column(name="email_address")
    private String emailAddress;

    @Column(name="phone1")
    private String phone1;

    @Column(name="phone2")
    private String phone2;

    @Column(name="phone3")
    private String phone3;

    @Column(name="creation_date")
    private Timestamp creationDate;
}
