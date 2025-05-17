package com.project_technique.project_technique.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationRequestDTO {
    private Long logementId;
    private LocalDate startDate;
    private LocalDate endDate;
}
