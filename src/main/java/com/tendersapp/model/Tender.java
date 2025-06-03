package com.tendersapp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.ZonedDateTime;

@Entity
@Data
@Table(name = "tenders")
public class Tender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "budget")
    private float budget;

    @Column(name = "deadline")
    private ZonedDateTime deadline;
    
    @Column(name = "region")
    private String region;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @ManyToOne(optional = false)
    @JoinColumn(name = "creator", nullable = false)
    private Account creator;

    @ManyToOne
    @JoinColumn(name = "winner")
    private Proposal winner;
}