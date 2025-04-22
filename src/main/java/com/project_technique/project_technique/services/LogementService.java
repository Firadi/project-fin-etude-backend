package com.project_technique.project_technique.services;

import com.project_technique.project_technique.models.Logement;
import com.project_technique.project_technique.repositories.LogementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogementService {

    @Autowired
    LogementRepo logementRepo;

    public Logement createLogement(Logement logement){
        return logementRepo.save(logement);
    }

    public List<Logement> getAllLogements() {
        return logementRepo.findAll();
    }
}
