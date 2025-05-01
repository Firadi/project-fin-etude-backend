package com.project_technique.project_technique.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project_technique.project_technique.models.logement.Logement;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Employe extends User{


//
    @Enumerated(EnumType.STRING)
    private RoleEmploye role; // DIRECTEUR, COMMERCIAL, GERANT

    @ManyToOne
    private AgenceImmobilier agence;

    @OneToMany(mappedBy = "employe")
    @JsonManagedReference
    @JsonIgnore
    private List<Logement> logements;
}
