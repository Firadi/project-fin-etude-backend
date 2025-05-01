package com.project_technique.project_technique.models.logement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project_technique.project_technique.models.Address;
import com.project_technique.project_technique.models.Employe;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Logement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;


    @Embedded
    private Address address;



    @Schema(example = "Villa")
    @Enumerated(EnumType.STRING)
    private LogementType type;

    private Integer capacity;
    private Integer nombreOfChambres;
    private Double pricePerNight;


//    @ManyToMany
//    @JoinTable(
//            name = "logement_equipements",
//            joinColumns = @JoinColumn(name = "logement_id"),
//            inverseJoinColumns = @JoinColumn(name = "equipement_id")
//    )
    //private List<Equipement> equipements;

    private List<String> equipement;

    @OneToMany(mappedBy = "logement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "commercial_id")
    @JsonBackReference
    private Employe employe;


}
