package com.project_technique.project_technique.services;

import com.project_technique.project_technique.dto.LogementRequestDTO;
import com.project_technique.project_technique.dto.LogementResponseDTO;
import com.project_technique.project_technique.models.Employe;
import com.project_technique.project_technique.models.logement.Image;
import com.project_technique.project_technique.models.logement.Logement;
import com.project_technique.project_technique.repositories.EmployeRepo;
import com.project_technique.project_technique.repositories.EquipementRepo;
import com.project_technique.project_technique.repositories.LogementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogementService {

    @Autowired
    LogementRepo logementRepo;

    @Autowired
    EquipementRepo equipementRepo;

    @Autowired
    EmployeRepo employeRepo;

    public Logement createLogement(LogementRequestDTO dto){
        Logement logement = new Logement();
        logement.setTitle(dto.getTitle());
        logement.setDescription(dto.getDescription());
        logement.setAddress(dto.getAddress());
        logement.setType(dto.getType());
        logement.setCapacity(dto.getCapacity());
        logement.setNombreOfChambres(dto.getNombreOfChambres());
        logement.setPricePerNight(dto.getPricePerNight());

        // Attacher l'employé (propriétaire)
        Employe employe = employeRepo.findById(dto.getEmployeId())
                .orElseThrow(() -> new RuntimeException("Employé non trouvé"));
        logement.setEmploye(employe);

        // Attacher les équipements
        //List<Equipement> equipements = equipementRepo.findAllById(dto.getEquipementIds());
        logement.setEquipement(dto.getEquipement());


        if (dto.getImagesBase64() != null) {
            List<Image> images = dto.getImagesBase64().stream().map(
                    base64 -> {
                        Image image = new Image();
                        image.setData( Base64.getDecoder().decode(base64) );
                        image.setLogement(logement);
                        return image;
                    }
            ).toList();

            logement.setImages(images);
        }
        return logementRepo.save(logement);
    }

    public List<LogementResponseDTO> getAllLogements() {
        return logementRepo.findAll().stream().map(
                logement -> {
                    LogementResponseDTO dto = new LogementResponseDTO();
                    dto.mapToDTO(logement);
                return dto;
        }).collect(Collectors.toList());
    }
}
