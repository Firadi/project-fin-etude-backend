package com.project_technique.project_technique.controllers;

import com.project_technique.project_technique.models.Proprietaire;
import com.project_technique.project_technique.services.ProprietaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/proprietaires")
@RestController
public class ProprietaireController {

    @Autowired
    ProprietaireService proprietaireService;


    @GetMapping
    public ResponseEntity<List<Proprietaire>> getProprietaire(){

        List<Proprietaire> list = new ArrayList<>();
        Proprietaire l = new Proprietaire();
        l.setEmail("hi");
        list.add(l);

        //return ResponseEntity.ok(list);
        return ResponseEntity.ok(proprietaireService.getAllProprietaires());
    }

    @PostMapping
    public Proprietaire createProprietaire(@RequestBody Proprietaire proprietaire){
        return proprietaireService.createProprietaire(proprietaire);
    }
}
