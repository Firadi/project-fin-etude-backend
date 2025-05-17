package com.project_technique.project_technique.services;

import com.project_technique.project_technique.models.Voyageur;
import com.project_technique.project_technique.repositories.VoyageurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoyageurService {

    @Autowired
    private VoyageurRepo voyageurRepo;

    public List<Voyageur> getAllVoyageurs() {
        return voyageurRepo.findAll();
    }

    public Voyageur getVoyageurById(Long id) {
        return voyageurRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Voyageur not found with ID: " + id));
    }

    public void deleteVoyageur(Long id) {
        voyageurRepo.deleteById(id);
    }

}
