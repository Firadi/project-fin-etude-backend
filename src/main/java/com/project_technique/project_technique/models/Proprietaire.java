package com.project_technique.project_technique.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Proprietaire extends User{

    private String companyName;

    @OneToMany(mappedBy = "proprietaire")
    @JsonManagedReference
    private List<Logement> logements;
}
