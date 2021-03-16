package edu.brandeis.gps.rseg127.lms.entities;

import java.sql.Timestamp;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class AssetCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="status")
    private String status;

    @Column(name="due_date")
    private Timestamp dueDate;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="asset_id")
    private Integer assetId;
}
