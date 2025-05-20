package com.project_technique.project_technique.services;

import com.project_technique.project_technique.dto.CreateAgenceWithDirecteurRequest;
import com.project_technique.project_technique.dto.EmployeRequest;
import com.project_technique.project_technique.models.AgenceImmobilier;
import com.project_technique.project_technique.models.Employe;
import com.project_technique.project_technique.models.RoleEmploye;
import com.project_technique.project_technique.repositories.AgenceImmoubilerRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgenceImmobilierService {
    @Autowired
    private AgenceImmoubilerRepo agenceImmoubilerRepo;

    @Autowired
    private EmployeService employeService;

    public List<AgenceImmobilier> getAllAgences() {
        return agenceImmoubilerRepo.findAll();
    }

    public Optional<AgenceImmobilier> getAgenceById(Long id) {
        return agenceImmoubilerRepo.findById(id);
    }

    public AgenceImmobilier createAgence(AgenceImmobilier agence) {
        return agenceImmoubilerRepo.save(agence);
    }

    @Transactional
    public AgenceImmobilier createAgenceWithDirecteur(CreateAgenceWithDirecteurRequest request) {
        // Step 1: Create the Agence
        AgenceImmobilier agence = new AgenceImmobilier();
        agence.setNom(request.nom());
        agence.setTel(request.tel());
        agence = agenceImmoubilerRepo.save(agence);

        // Step 2: Create the Directeur (Employe)

        EmployeRequest directeur = new EmployeRequest(
                request.directeurFirstName(),
                request.directeurLastName(),
                request.directeurEmail(),
                request.directeurPassword(),
                RoleEmploye.DIRECTEUR,
                agence.getId()
        );

        Employe dir = employeService.createEmploye(directeur);

        // Step 3: Update Agence with Directeur
        agence.setDirecteur(dir);
        agence = agenceImmoubilerRepo.save(agence);

        return agence;
    }

    public AgenceImmobilier updateAgence(Long id, AgenceImmobilier updatedAgence) {
        return agenceImmoubilerRepo.findById(id)
                .map(agence -> {
                    agence.setNom(updatedAgence.getNom());
                    agence.setTel(updatedAgence.getTel());
                    agence.setEmail(updatedAgence.getEmail());
                    agence.setDirecteur(updatedAgence.getDirecteur());
                    agence.setEmployes(updatedAgence.getEmployes());
                    return agenceImmoubilerRepo.save(agence);
                })
                .orElseGet(() -> {
                    updatedAgence.setId(id);
                    return agenceImmoubilerRepo.save(updatedAgence);
                });
    }

    public void deleteAgence(Long id) {
        agenceImmoubilerRepo.deleteById(id);
    }
}
