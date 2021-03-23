package edu.brandeis.gps.rseg127.lms.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.Getter;

@Entity
@Immutable
@Getter
@Table(name="copy_asset_user_vw")
public class CopyAssetUserView {
    @Id
    private Integer copyId;

    private Integer assetId;

    private String title;

    private String isbn;
    
    private Integer userId;

    private String username;

    private String status;

    private Timestamp dueDate;
}
