package de.monir.example.emails.repository;

import de.monir.example.emails.model.Email;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Hidden
@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
}