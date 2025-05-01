package com.project_technique.project_technique.dto;

import com.project_technique.project_technique.models.Address;
import com.project_technique.project_technique.models.logement.LogementType;
import lombok.Data;

import java.util.List;

@Data
public class LogementRequestDTO {
    private String title;
    private Address address;
    private LogementType type;
    private Integer capacity;
    private String description;
    private Integer nombreOfChambres;
    private Double pricePerNight;
    private Long employeId; // propriétaire
    //private List<Long> equipementIds;
    private List<String> equipement;
    private List<String> imagesBase64;
}
