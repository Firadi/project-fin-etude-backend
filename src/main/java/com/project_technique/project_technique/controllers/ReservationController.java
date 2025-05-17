package com.project_technique.project_technique.controllers;

import com.project_technique.project_technique.dto.ReservationRequestDTO;
import com.project_technique.project_technique.models.Reservation;
import com.project_technique.project_technique.models.ReservationStatus;
import com.project_technique.project_technique.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationRequestDTO request, Authentication authentication) {

        String email = authentication.getName();

        Reservation reservation = reservationService.createReservation(
                request.getLogementId(),
                email,
                request.getStartDate(),
                request.getEndDate()
        );
        return ResponseEntity.ok(reservation);
    }

    @GetMapping("/voyageur/{id}")
    public ResponseEntity<List<Reservation>> getReservationsForVoyageur(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationsForVoyageur(id));
    }

    @GetMapping("/logement/{id}")
    public ResponseEntity<List<Reservation>> getReservationsForLogement(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationsForLogement(id));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Reservation> updateStatus(@PathVariable Long id, @RequestParam ReservationStatus status) {
        return ResponseEntity.ok(reservationService.updateStatus(id, status));
    }
}
