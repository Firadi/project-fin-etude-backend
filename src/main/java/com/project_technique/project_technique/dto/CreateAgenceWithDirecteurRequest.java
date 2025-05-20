package com.project_technique.project_technique.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record CreateAgenceWithDirecteurRequest(

        @Schema(example = "Square Holy")
        @NotBlank(message = "Le nom de l'agence est requis")
        String nom,

        @NotBlank(message = "Le numéro de téléphone est requis")
        String tel,

        @Schema(example = "Ahmed")
        @NotBlank(message = "Le prénom du directeur est requis")
        String directeurFirstName,

        @Schema(example = "Moussa")
        @NotBlank(message = "Le nom du directeur est requis")
        String directeurLastName,

        @Email(message = "Format d'email invalide")
        @NotBlank(message = "L'email du directeur est requis")
        @Schema(example = "email@gmail.com")
        String directeurEmail,

        @NotBlank(message = "Le mot de passe est requis")
        @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères")
        String directeurPassword

) {}
