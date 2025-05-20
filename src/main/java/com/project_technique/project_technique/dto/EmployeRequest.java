package com.project_technique.project_technique.dto;

import com.project_technique.project_technique.models.AgenceImmobilier;
import com.project_technique.project_technique.models.Employe;
import com.project_technique.project_technique.models.RoleEmploye;


public record EmployeRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        RoleEmploye role

) {
    public Employe toEmploye(String encodedPassword, AgenceImmobilier agence) {
        Employe employe = new Employe();
        employe.setFirstName(this.firstName());
        employe.setLastName(this.lastName());
        employe.setEmail(this.email());
        employe.setPassword(encodedPassword);
        employe.setRole(this.role());
        employe.setAgence(agence);
        return employe;
    }
}

