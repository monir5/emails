package de.monir.example.emails.service;

import de.monir.example.emails.dto.EmailUpdateDTO;
import de.monir.example.emails.model.Email;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface EmailService {

    Email create(Email email);
    List<Email> createBulk(List<Email> emails);

    Email update(Long emailId, EmailUpdateDTO emailUpdateDTO);

    void delete(Long emailId);
    void deleteBulk(List<Long> emailIds);

    List<Email> findAll();
    Optional<Email> findById(Long id);
    List<Email> findAllByFrom(String from);
}
