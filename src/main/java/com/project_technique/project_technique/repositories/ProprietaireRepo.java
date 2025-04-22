package com.project_technique.project_technique.repositories;

import com.project_technique.project_technique.models.Proprietaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProprietaireRepo extends JpaRepository<Proprietaire, Long> {
}
