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
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="actor_user")
    private String actor_user;

    @Column(name="action")
    private String action;

    @Column(name="target_user")
    private String target_user;

    @Column(name="copy_id")
    private Integer copy_id;

    @Column(name="action_date")
    private Timestamp action_date;
}
