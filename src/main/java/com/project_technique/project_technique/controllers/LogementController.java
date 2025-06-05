package com.project_technique.project_technique.controllers;

import com.project_technique.project_technique.annotations.IsDirecteur;
import com.project_technique.project_technique.dto.LogementRequestDTO;
import com.project_technique.project_technique.dto.LogementResponseDTO;
import com.project_technique.project_technique.models.Employe;
import com.project_technique.project_technique.models.logement.Logement;
import com.project_technique.project_technique.services.EmployeService;
import com.project_technique.project_technique.services.LogementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/logements")
@RestController
public class LogementController {

    @Autowired
    LogementService logementService;

    @Autowired
    EmployeService employeService;

    @GetMapping
    public ResponseEntity<List<LogementResponseDTO>> getLogements(){

        List<Logement> list = new ArrayList<>();
        Logement l = new Logement();
        l.setTitle("hi");
        list.add(l);

        //return ResponseEntity.ok(list);
        return ResponseEntity.ok(logementService.getAllLogements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogementResponseDTO> getLogementById(@PathVariable Long id){

        return logementService.getLogementById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/my-logements")
    public ResponseEntity<List<LogementResponseDTO>> getLogementsByCommercialToken(Authentication authentication) {

        String commercialEmail = extractCommercialEmail(authentication);
        List<LogementResponseDTO> logements = logementService.findByCommercialEmail(commercialEmail);
        return ResponseEntity.ok(logements);
    }


    @IsDirecteur
    @GetMapping("/commercial/{commercialId}")
    public ResponseEntity<List<LogementResponseDTO>> getLogementsByCommercial(@PathVariable Long commercialId) {


        List<LogementResponseDTO> logements = logementService.findByCommercialId(commercialId);
        return ResponseEntity.ok(logements);
    }

    @PostMapping
    public ResponseEntity<Logement> createLogement(@RequestBody LogementRequestDTO dto, Authentication authentication){

        String commercialEmail = extractCommercialEmail(authentication);

        Employe employe = employeService.findByEmail(commercialEmail);

        if (employe == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        dto.setEmployeId(employe.getId());

        Logement createdLogement = logementService.createLogement(dto);
        return ResponseEntity.ok(createdLogement);
    }




    private String extractCommercialEmail(Authentication authentication) {


        String commercialIdentifier = authentication.getName();

        if (authentication.getPrincipal() instanceof Map claims) {
            return (String) claims.get("commercialEmail");
        }


        return authentication.getName(); // email
    }



}
