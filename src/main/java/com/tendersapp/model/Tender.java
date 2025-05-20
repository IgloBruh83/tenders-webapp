package com.tendersapp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Data
@Table(name = "tenders")
public class Tender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private String status;

    @Column(name = "budget")
    private BigDecimal budget;

    @Column(name = "deadline")
    private ZonedDateTime deadline;

    @Column(name = "currencyCode")
    private int currencyCode;

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
    private Account winner;
}