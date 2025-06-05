package com.project_technique.project_technique.repositories;

import com.project_technique.project_technique.models.Employe;
import com.project_technique.project_technique.models.logement.Logement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogementRepo extends JpaRepository<Logement, Long> {

    List<Logement> findByEmploye(Employe employe);
    // OR if you want to use the employee ID instead
    List<Logement> findByEmployeEmail(String employeEmail);

    List<Logement> findByEmployeId(Long commercialId);
}
