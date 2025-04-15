package com.tendersapp.model;

import lombok.Data;
import java.time.ZonedDateTime;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @Column(name = "tax_id")
    private String taxId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "email")
    private String email;

    @Column(name = "login")
    private String login;

    @Column(name = "password_hash")
    private String password;

    @Column(name = "tel")
    private String tel;

    @Column(name = "tel2")
    private String tel2;

    @Column(name = "region")
    private String region;

    @Column(name = "post_index")
    private String postIndex;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "register_date")
    private ZonedDateTime registerDate;

    @Column(name = "deletion_date")
    private ZonedDateTime deletionDate;

    @Column(name = "suspended")
    private boolean suspended;

}