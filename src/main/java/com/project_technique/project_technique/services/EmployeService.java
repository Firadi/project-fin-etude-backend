package com.project_technique.project_technique.services;

import com.project_technique.project_technique.dto.EmployeRequest;
import com.project_technique.project_technique.exception.EmailAlreadyExistsException;
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

    @Autowired
    private JwtUtil jwtUtil;





    public List<Employe> getAllEmployes() {
        return employeRepo.findAll();
    }

    public Optional<Employe> getEmployeById(Long id) {
        return employeRepo.findById(id);
    }


    public Employe createCommercial(EmployeRequest dto) {

        if (userRepo.existsByEmail(dto.email())) {
            throw new EmailAlreadyExistsException("Email already exist");
        }

        String directeurEmail = jwtUtil.getCurrentUserEmail();

        Employe directeur = employeRepo.findByEmail(directeurEmail)
                .orElseThrow(() -> new RuntimeException("Directeur not found"));

        AgenceImmobilier agence = directeur.getAgence();


        String encodedPassword = passwordEncoder.encode(dto.password());

        Employe employe = dto.toEmploye(encodedPassword, agence);


        // Save
        return employeRepo.save(employe);
    }

    public Employe createDirecteur(EmployeRequest dto, AgenceImmobilier agence){
        if (userRepo.existsByEmail(dto.email())) {
            throw new EmailAlreadyExistsException("Email already exist");
        }

        String encodedPassword = passwordEncoder.encode(dto.password());
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
