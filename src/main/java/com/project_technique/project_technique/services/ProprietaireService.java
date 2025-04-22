package com.project_technique.project_technique.services;

import com.project_technique.project_technique.models.Proprietaire;
import com.project_technique.project_technique.repositories.ProprietaireRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProprietaireService {

    @Autowired
    private ProprietaireRepo proprietaireRepo;

    public List<Proprietaire> getAllProprietaires() {
        return proprietaireRepo.findAll();
    }

    public Optional<Proprietaire> getProprietaireById(Long id) {
        return proprietaireRepo.findById(id);
    }

    public Proprietaire createProprietaire(Proprietaire proprietaire) {
        return proprietaireRepo.save(proprietaire);
    }

    public Proprietaire updateProprietaire(Long id, Proprietaire updatedProprietaire) {
        return proprietaireRepo.findById(id)
                .map(p -> {
                    p.setName(updatedProprietaire.getName());
                    p.setEmail(updatedProprietaire.getEmail());
                    p.setPassword(updatedProprietaire.getPassword());
                    p.setCompanyName(updatedProprietaire.getCompanyName());
                    return proprietaireRepo.save(p);
                })
                .orElseThrow(() -> new RuntimeException("Propriétaire non trouvé"));
    }

    public void deleteProprietaire(Long id) {
        proprietaireRepo.deleteById(id);
    }

}
