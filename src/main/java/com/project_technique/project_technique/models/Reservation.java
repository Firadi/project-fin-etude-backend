package com.project_technique.project_technique.models;

import com.project_technique.project_technique.models.logement.Logement;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @ManyToOne
    @JoinColumn(name = "voyageur_id")
    private User voyageur;

    @ManyToOne
    @JoinColumn(name = "logement_id")
    private Logement logement;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        status = ReservationStatus.PENDING;
    }

}
