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
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="actor_user")
    private String actorUser;

    @Column(name="action")
    private String action;

    @Column(name="target_user")
    private String targetUser;

    @Column(name="copy_id")
    private Integer copyId;

    @Setter(AccessLevel.NONE)
    @Column(name="action_date")
    private Timestamp actionDate;
}
