package com.project_technique.project_technique.repositories;

import com.project_technique.project_technique.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    List<Reservation> findByVoyageurId(Long voyageurId);
    List<Reservation> findByLogementId(Long logementId);
}
