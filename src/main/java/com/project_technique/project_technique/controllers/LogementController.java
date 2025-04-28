package com.project_technique.project_technique.controllers;

import com.project_technique.project_technique.dto.LogementRequestDTO;
import com.project_technique.project_technique.models.Logement;
import com.project_technique.project_technique.services.LogementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/logements")
@RestController
public class LogementController {

    @Autowired
    LogementService logementService;

    @GetMapping
    public ResponseEntity<List<Logement>> getLogements(){

        List<Logement> list = new ArrayList<>();
        Logement l = new Logement();
        l.setTitle("hi");
        list.add(l);

        //return ResponseEntity.ok(list);
        return ResponseEntity.ok(logementService.getAllLogements());
    }

    @PostMapping
    public ResponseEntity<Logement> createLogement(@RequestBody LogementRequestDTO dto){
        Logement createdLogement = logementService.createLogement(dto);
        return ResponseEntity.ok(createdLogement);
    }


}
