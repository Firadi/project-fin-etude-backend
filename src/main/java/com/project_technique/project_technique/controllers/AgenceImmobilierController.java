package com.project_technique.project_technique.controllers;

import com.project_technique.project_technique.dto.CreateAgenceWithDirecteurRequestDTO;
import com.project_technique.project_technique.models.AgenceImmobilier;
import com.project_technique.project_technique.services.AgenceImmobilierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/agences")
public class AgenceImmobilierController {
    @Autowired
    private AgenceImmobilierService agenceImmobilierService;

    @GetMapping
    public List<AgenceImmobilier> getAllAgences() {
        return agenceImmobilierService.getAllAgences();
    }

    @GetMapping("/{id}")
    public Optional<AgenceImmobilier> getAgenceById(@PathVariable Long id) {
        return agenceImmobilierService.getAgenceById(id);
    }

//    @PostMapping
//    public AgenceImmobilier createAgence(@RequestBody AgenceImmobilier agence) {
//        return agenceImmobilierService.createAgence(agence);
//    }

    @PostMapping
    public AgenceImmobilier createAgenceWithDirecteur(@RequestBody CreateAgenceWithDirecteurRequestDTO request) {
        return agenceImmobilierService.createAgenceWithDirecteur(request);
    }

    @PutMapping("/{id}")
    public AgenceImmobilier updateAgence(@PathVariable Long id, @RequestBody AgenceImmobilier agence) {
        return agenceImmobilierService.updateAgence(id, agence);
    }

    @DeleteMapping("/{id}")
    public void deleteAgence(@PathVariable Long id) {
        agenceImmobilierService.deleteAgence(id);
    }
}
