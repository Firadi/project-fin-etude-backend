package com.project_technique.project_technique.repositories;

import com.project_technique.project_technique.models.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeRepo extends JpaRepository<Employe, Long> {
    public Optional<Employe> findByEmail(String email);
}
