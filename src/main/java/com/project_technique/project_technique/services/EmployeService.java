package com.project_technique.project_technique.services;

import com.project_technique.project_technique.dto.EmployeRequestDTO;
import com.project_technique.project_technique.models.AgenceImmobilier;
import com.project_technique.project_technique.models.Employe;
import com.project_technique.project_technique.repositories.AgenceImmoubilerRepo;
import com.project_technique.project_technique.repositories.EmployeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeService {

    @Autowired
    private EmployeRepo employeRepository;

    @Autowired
    private AgenceImmoubilerRepo agenceImmoubilerRepo;


    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }

    public Optional<Employe> getEmployeById(Long id) {
        return employeRepository.findById(id);
    }

    public Employe createEmploye(EmployeRequestDTO dto) {

        AgenceImmobilier agence = agenceImmoubilerRepo.findById(dto.getAgenceId())
                .orElseThrow(() -> new RuntimeException("Agence not found"));

        // Create the Employe manually
        Employe employe = new Employe();
        employe.setName(dto.getName());
        employe.setEmail(dto.getEmail());
        employe.setPassword(dto.getPassword());
        employe.setRole(dto.getRole());
        employe.setAgence(agence);

        // Save
        return employeRepository.save(employe);
    }

    public Employe updateEmploye(Long id, Employe updatedEmploye) {
        return employeRepository.findById(id)
                .map(employe -> {
                    employe.setRole(updatedEmploye.getRole());
                    employe.setAgence(updatedEmploye.getAgence());
                    employe.setLogements(updatedEmploye.getLogements());
                    return employeRepository.save(employe);
                })
                .orElseGet(() -> {
                    updatedEmploye.setId(id);
                    return employeRepository.save(updatedEmploye);
                });
    }

    public void deleteEmploye(Long id) {
        employeRepository.deleteById(id);
    }

}
