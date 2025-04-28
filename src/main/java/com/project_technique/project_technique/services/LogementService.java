package com.project_technique.project_technique.services;

import com.project_technique.project_technique.dto.LogementRequestDTO;
import com.project_technique.project_technique.models.Employe;
import com.project_technique.project_technique.models.Equipement;
import com.project_technique.project_technique.models.Logement;
import com.project_technique.project_technique.repositories.EmployeRepo;
import com.project_technique.project_technique.repositories.EquipementRepo;
import com.project_technique.project_technique.repositories.LogementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return logementRepo.save(logement);
    }

    public List<Logement> getAllLogements() {
        return logementRepo.findAll();
    }
}
