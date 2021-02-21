package edu.brandeis.gps.rseg127.lms.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="username")
    private String username;

    @Column(name="password_hash")
    private String password_hash;

    @Column(name="patron_id")
    private String patron_id;

    @Column(name="first_name")
    private String first_name;

    @Column(name="middle_name")
    private String middle_name;

    @Column(name="last_name")
    private String last_name;

    @Column(name="home_address")
    private String home_address;

    @Column(name="mail_address")
    private String mail_address;

    @Column(name="email_address")
    private String email_address;

    @Column(name="phone1")
    private String phone1;

    @Column(name="phone2")
    private String phone2;

    @Column(name="phone3")
    private String phone3;

    @Column(name="creation_date")
    private Timestamp creation_date;
}
