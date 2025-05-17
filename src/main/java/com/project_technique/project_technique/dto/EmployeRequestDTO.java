package com.project_technique.project_technique.dto;

import com.project_technique.project_technique.models.AgenceImmobilier;
import com.project_technique.project_technique.models.Employe;
import com.project_technique.project_technique.models.RoleEmploye;
import lombok.Data;

@Data
public class EmployeRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private RoleEmploye role;
    private Long agenceId;


    public Employe toEmploye(String encodedPassword, AgenceImmobilier agence){
        Employe employe = new Employe();
        employe.setFirstName(this.getFirstName());
        employe.setLastName(this.getLastName());
        employe.setEmail(this.getEmail());
        employe.setPassword(encodedPassword);
        employe.setRole(this.getRole());
        employe.setAgence(agence);
        return employe;
    }
}
