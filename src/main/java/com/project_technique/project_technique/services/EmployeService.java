package com.project_technique.project_technique.services;

import com.project_technique.project_technique.dto.EmployeRequestDTO;
import com.project_technique.project_technique.models.AgenceImmobilier;
import com.project_technique.project_technique.models.Employe;
import com.project_technique.project_technique.models.RoleEmploye;
import com.project_technique.project_technique.repositories.AgenceImmoubilerRepo;
import com.project_technique.project_technique.repositories.EmployeRepo;
import com.project_technique.project_technique.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeService {

    @Autowired
    private EmployeRepo employeRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AgenceImmoubilerRepo agenceImmoubilerRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Employe> getAllEmployes() {
        return employeRepo.findAll();
    }

    public Optional<Employe> getEmployeById(Long id) {
        return employeRepo.findById(id);
    }

    public Employe createEmploye(EmployeRequestDTO dto) {

        if (userRepo.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already exists.");
        }

        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        AgenceImmobilier agence = agenceImmoubilerRepo.findById(dto.getAgenceId())
                .orElseThrow(() -> new RuntimeException("Agence not found"));

        // Create the Employe manually
        Employe employe = dto.toEmploye(encodedPassword, agence);


        // Save
        return employeRepo.save(employe);
    }

    public Employe updateEmploye(Long id, Employe updatedEmploye) {
        return employeRepo.findById(id)
                .map(employe -> {
                    employe.setRole(updatedEmploye.getRole());
                    employe.setAgence(updatedEmploye.getAgence());
                    employe.setLogements(updatedEmploye.getLogements());
                    return employeRepo.save(employe);
                })
                .orElseGet(() -> {
                    updatedEmploye.setId(id);
                    return employeRepo.save(updatedEmploye);
                });
    }

    public void deleteEmploye(Long id) {
        employeRepo.deleteById(id);
    }

    public Employe findByEmail(String email) {

        return employeRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employé non trouvé"));
    }

    public List<Employe> findByAgenceAndRole(AgenceImmobilier agenceImmobilier, RoleEmploye roleEmploye) {
        return employeRepo.findByAgenceAndRole(agenceImmobilier, roleEmploye);
    }
}
