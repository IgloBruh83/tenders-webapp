/*

package com.tendersapp.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "documents")
@Check(constraints =
        "(tender_id is not null AND proposal_id is null) " +
                "OR (tender_id is null AND proposal_id is not null)"
)
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String url;

    @Column(nullable = true)
    private String filename;

    @ManyToOne
    @JoinColumn(name = "tender_id", nullable = true)
    private Tender tender;

    @ManyToOne
    @JoinColumn(name = "proposal_id", nullable = true)
    private Tender proposal;
}


*/