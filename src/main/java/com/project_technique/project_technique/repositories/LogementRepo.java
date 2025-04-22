package com.project_technique.project_technique.repositories;

import com.project_technique.project_technique.models.Logement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogementRepo extends JpaRepository<Logement, Long> {
}
