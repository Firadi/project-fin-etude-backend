package com.project_technique.project_technique.services;

import com.project_technique.project_technique.dto.CreateAgenceWithDirecteurRequestDTO;
import com.project_technique.project_technique.models.AgenceImmobilier;
import com.project_technique.project_technique.models.Employe;
import com.project_technique.project_technique.models.RoleEmploye;
import com.project_technique.project_technique.repositories.AgenceImmoubilerRepo;
import com.project_technique.project_technique.repositories.EmployeRepo;
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
    private EmployeRepo employeRepo;

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
    public AgenceImmobilier createAgenceWithDirecteur(CreateAgenceWithDirecteurRequestDTO request) {
        // Step 1: Create the Agence
        AgenceImmobilier agence = new AgenceImmobilier();
        agence.setNom(request.getNom());
        agence.setTel(request.getTel());
        agence.setEmail(request.getEmail());
        agence = agenceImmoubilerRepo.save(agence);

        // Step 2: Create the Directeur (Employe)
        Employe directeur = new Employe();
        directeur.setName(request.getDirecteurName());
        directeur.setEmail(request.getDirecteurEmail());
        directeur.setPassword(request.getDirecteurPassword());
        directeur.setRole(RoleEmploye.DIRECTEUR);
        directeur.setAgence(agence);
        directeur = employeRepo.save(directeur);

        // Step 3: Update Agence with Directeur
        agence.setDirecteur(directeur);
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
