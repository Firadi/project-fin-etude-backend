package com.project_technique.project_technique.services;

import com.project_technique.project_technique.models.Reservation;
import com.project_technique.project_technique.models.ReservationStatus;
import com.project_technique.project_technique.models.User;
import com.project_technique.project_technique.models.logement.Logement;
import com.project_technique.project_technique.repositories.LogementRepo;
import com.project_technique.project_technique.repositories.ReservationRepo;
import com.project_technique.project_technique.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private LogementRepo logementRepo;

    @Autowired
    private UserRepo userRepo;

    public Reservation createReservation(Long logementId, String voyageurEmail, LocalDate startDate, LocalDate endDate) {
        Logement logement = logementRepo.findById(logementId)
                .orElseThrow(() -> new RuntimeException("Logement not found"));
        User voyageur = userRepo.findByEmail(voyageurEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Reservation reservation = new Reservation();
        reservation.setLogement(logement);
        reservation.setVoyageur(voyageur);
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setStatus(ReservationStatus.PENDING);

        return reservationRepo.save(reservation);
    }

    public List<Reservation> getReservationsForVoyageur(Long voyageurId) {
        return reservationRepo.findByVoyageurId(voyageurId);
    }

    public List<Reservation> getReservationsForLogement(Long logementId) {
        return reservationRepo.findByLogementId(logementId);
    }

    public Reservation updateStatus(Long reservationId, ReservationStatus status) {
        Reservation res = reservationRepo.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        res.setStatus(status);
        return reservationRepo.save(res);
    }
}
