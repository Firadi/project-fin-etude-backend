package com.project_technique.project_technique.repositories;

import com.project_technique.project_technique.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String voyageurEmail);
    boolean existsByEmail(String email);
}
