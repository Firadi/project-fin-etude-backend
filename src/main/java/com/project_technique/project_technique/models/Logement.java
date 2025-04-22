package com.project_technique.project_technique.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Logement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String address;

    @Enumerated(EnumType.STRING)
    private LogementType type;

    private Integer capacity;
    private Integer nombreOfChambres;
    private Double pricePerNight;

    @ManyToOne
    @JoinColumn(name = "proprietaire_id")
    @JsonBackReference
    private Proprietaire proprietaire;
}
