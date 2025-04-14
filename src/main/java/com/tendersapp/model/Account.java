package com.tendersapp.model;

import lombok.Data;
import java.time.ZonedDateTime;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "accounts")
public class Account {

    // FIELDS
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

    @Column(name = "password")
    private String password;

    @Column(name = "tel")
    private String tel;

    @Column(name = "tel2")
    private String tel2;

    @Column(name = "register_date")
    private ZonedDateTime registerDate;

    @Column(name = "deletion_date")
    private ZonedDateTime deletionDate;

}