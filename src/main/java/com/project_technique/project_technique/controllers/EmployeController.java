package com.project_technique.project_technique.controllers;

import com.project_technique.project_technique.annotations.IsDirecteur;
import com.project_technique.project_technique.dto.EmployeRequest;
import com.project_technique.project_technique.models.AgenceImmobilier;
import com.project_technique.project_technique.models.Employe;
import com.project_technique.project_technique.models.RoleEmploye;

import com.project_technique.project_technique.services.EmployeService;
import com.project_technique.project_technique.services.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeController {

    @Autowired
    private EmployeService employeService;






    @GetMapping
    public List<Employe> getAllEmployes() {
        return employeService.getAllEmployes();
    }

    @GetMapping("/{id}")
    public Optional<Employe> getEmployeById(@PathVariable Long id) {
        return employeService.getEmployeById(id);
    }

    @Operation(summary = "Get all commercials by agence (only for DIRECTEUR)",
            security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/commercials")
    @IsDirecteur
    public ResponseEntity<List<Employe>> getCommercialsByAgence(Authentication authentication){
        String email = authentication.getName();

        Employe directeur = employeService.findByEmail(email);


        AgenceImmobilier agenceImmobilier = directeur.getAgence();

        List<Employe> commerciaux = employeService.findByAgenceAndRole(agenceImmobilier, RoleEmploye.COMMERCIAL);

        return ResponseEntity.ok(commerciaux);
    }

    @PostMapping
    @IsDirecteur
    public ResponseEntity<Employe> createEmploye(@RequestBody EmployeRequest dto) {



        return ResponseEntity
                .status( HttpStatus.CREATED )
                .body(employeService.createEmploye(dto));
    }

    @PutMapping("/{id}")
    public Employe updateEmploye(@PathVariable Long id, @RequestBody Employe employe) {
        return employeService.updateEmploye(id, employe);
    }

    @DeleteMapping("/{id}")
    public void deleteEmploye(@PathVariable Long id) {
        employeService.deleteEmploye(id);
    }

}
