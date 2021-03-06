package edu.brandeis.gps.rseg127.lms.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message="Title is mandatory.")
    @Column(name="title")
    private String title;

    @NotBlank(message="ISBN is mandatory.")
    @Column(name="isbn")
    private String isbn;

    @NotBlank(message="Call number is mandatory.")
    @Column(name="call_number")
    private String callNumber;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "asset_author", joinColumns = @JoinColumn(name = "asset_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AssetCopy> copies = new HashSet<>();
}
