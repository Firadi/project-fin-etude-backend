package com.project_technique.project_technique.dto;

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
}
