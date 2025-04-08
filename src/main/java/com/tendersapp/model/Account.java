package com.tendersapp.model;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import jakarta.persistence.*;

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

    // CONSTRUCTORS
    public Account() {}

    public Account(String tax_id, String full_name, String short_name,
                   String email, String login, String password,
                   String tel, String tel2) {
        this.taxId = tax_id;
        this.fullName = full_name;
        this.shortName = short_name;
        this.email = email;
        this.login = login;
        this.password = password;
        this.tel = tel;
        this.tel2 = tel2;
        this.registerDate = ZonedDateTime.now(ZoneOffset.UTC);
    }

    // GETTERS & SETTERS


    public String getTax_id() {
        return taxId;
    }


    public String getFull_name() {
        return fullName;
    }

    public void setFull_name(String full_name) {
        this.fullName = full_name;
    }

    public String getShort_name() {
        return shortName;
    }

    public void setShort_name(String short_name) {
        this.shortName = short_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public ZonedDateTime getRegister_date() {
        return registerDate;
    }

    public ZonedDateTime getDeletion_date() {
        return deletionDate;
    }

    public void setDeletion_date() {
        this.deletionDate = ZonedDateTime.now(ZoneOffset.UTC);
    }
}