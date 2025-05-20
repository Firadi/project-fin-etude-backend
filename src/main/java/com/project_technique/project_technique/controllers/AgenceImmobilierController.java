package com.project_technique.project_technique.controllers;

import com.project_technique.project_technique.dto.CreateAgenceWithDirecteurRequest;
import com.project_technique.project_technique.models.AgenceImmobilier;
import com.project_technique.project_technique.services.AgenceImmobilierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<AgenceImmobilier> createAgenceWithDirecteur(@RequestBody @Valid CreateAgenceWithDirecteurRequest request) {

        AgenceImmobilier result = agenceImmobilierService.createAgenceWithDirecteur(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
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
