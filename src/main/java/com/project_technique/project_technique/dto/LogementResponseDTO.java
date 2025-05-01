package com.project_technique.project_technique.dto;

import com.project_technique.project_technique.models.Address;
import com.project_technique.project_technique.models.logement.Logement;
import com.project_technique.project_technique.models.logement.LogementType;
import lombok.Data;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class LogementResponseDTO {
    private Long id;
    private String title;
    private Address address;
    private LogementType type;
    private Integer capacity;
    private String description;
    private Integer nombreOfChambres;
    private Double pricePerNight;
    private Long employeId; // géstionére
    //private List<Long> equipementIds;
    private List<String> equipement;
    private List<String> imagesBase64;

    public void mapToDTO(Logement logement) {

        this.setId(logement.getId());
        this.setTitle(logement.getTitle());
        this.setDescription(logement.getDescription());
        this.setAddress(logement.getAddress());
        this.setType(logement.getType());
        this.setCapacity(logement.getCapacity());
        this.setNombreOfChambres(logement.getNombreOfChambres());
        this.setPricePerNight(logement.getPricePerNight());

        if (logement.getEmploye() != null) {
            this.setEmployeId(logement.getEmploye().getId());
        }

        if (logement.getEquipement() != null) {

            this.setEquipement(logement.getEquipement());
        }

        if (logement.getImages() != null) {
            List<String> base64Images = logement.getImages().stream()
                    .map(
                            image -> Base64.getEncoder()
                                        .encodeToString(image.getData()))
                    .collect(Collectors.toList());
            this.setImagesBase64(base64Images);
        }

    }
}
