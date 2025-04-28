package com.project_technique.project_technique.dto;

import lombok.Data;

@Data
public class CreateAgenceWithDirecteurRequestDTO {

    // Agence info
    private String nom;
    private String tel;
    private String email;

    // Directeur info
    private String directeurName;
    private String directeurEmail;
    private String directeurPassword;

}
