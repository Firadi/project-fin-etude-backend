package com.project_technique.project_technique.controllers;

import com.project_technique.project_technique.dto.EmployeRequestDTO;
import com.project_technique.project_technique.models.Employe;
import com.project_technique.project_technique.repositories.EmployeRepo;
import com.project_technique.project_technique.services.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<Employe> createEmploye(@RequestBody EmployeRequestDTO dto) {
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
