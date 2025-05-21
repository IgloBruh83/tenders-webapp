package com.tendersapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="proposals")
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "creator", nullable = false)
    private Account creator;

    @Column(name = "plan_length")
    private int planLength;

    @Column(name = "budget")
    private float budget;

    @Column(name = "description", length = 2000)
    private String description;


}
