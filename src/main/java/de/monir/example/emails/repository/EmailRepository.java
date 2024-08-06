package de.monir.example.emails.repository;

import de.monir.example.emails.model.Email;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Hidden
@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
    List<Email> findAllByFromContainingIgnoreCase(String from);
}