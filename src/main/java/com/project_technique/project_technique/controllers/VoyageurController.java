package com.project_technique.project_technique.controllers;

import com.project_technique.project_technique.models.Voyageur;
import com.project_technique.project_technique.services.VoyageurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voyageurs")
public class VoyageurController {

    @Autowired
    private VoyageurService voyageurService;

    @GetMapping
    public List<Voyageur> getAllVoyageurs() {
        return voyageurService.getAllVoyageurs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voyageur> getVoyageurById(@PathVariable Long id) {
        return ResponseEntity.ok(voyageurService.getVoyageurById(id));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoyageur(@PathVariable Long id) {
        voyageurService.deleteVoyageur(id);
        return ResponseEntity.noContent().build();
    }

}
