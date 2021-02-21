package edu.brandeis.gps.rseg127.lms.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="title")
    private String title;

    @Column(name="isbn")
    private String isbn;

    @Column(name="call_number")
    private String callNumber;
}
