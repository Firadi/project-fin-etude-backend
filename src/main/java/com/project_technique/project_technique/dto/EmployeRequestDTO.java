package com.project_technique.project_technique.dto;

import com.project_technique.project_technique.models.RoleEmploye;
import lombok.Data;

@Data
public class EmployeRequestDTO {
    private String name;
    private String email;
    private String password;
    private RoleEmploye role;
    private Long agenceId;
}
