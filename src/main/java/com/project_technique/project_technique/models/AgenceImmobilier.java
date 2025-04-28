package com.project_technique.project_technique.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class AgenceImmobilier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String tel;

    private String email;

    @OneToOne
    @JsonIgnore
    private Employe directeur;

    @OneToMany(mappedBy = "agence")
    @JsonIgnore
    private List<Employe> employes;
}
