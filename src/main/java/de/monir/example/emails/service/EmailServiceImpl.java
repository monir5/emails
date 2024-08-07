package de.monir.example.emails.service;

import de.monir.example.emails.dto.EmailUpdateDTO;
import de.monir.example.emails.exception.EmailNotAllowedToUpdateException;
import de.monir.example.emails.exception.EmailNotFoundException;
import de.monir.example.emails.mapper.EmailMapper;
import de.monir.example.emails.model.Email;
import de.monir.example.emails.model.State;
import de.monir.example.emails.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    private final EmailRepository emailRepository;
    private final EmailMapper emailMapper;

    @Override
    public Email create(Email email) {
        return emailRepository.save(email);
    }

    @Override
    public List<Email> createBulk(List<Email> emails) {
        return emailRepository.saveAll(emails);
    }

    @Override
    public Email update(Long emailId, EmailUpdateDTO emailUpdateDTO) {
        Optional<Email> emailOptional = emailRepository.findById(emailId);
        if (emailOptional.isEmpty()) {
            throw new EmailNotFoundException("Email with id " + emailId + " is not found.");
        }
        Email email = emailOptional.get();
        if (State.DRAFT.equals(email.getState())) {
            Email emailToSave = emailMapper.emailUpdateDTOToEmail(emailUpdateDTO);
            emailToSave.setId(emailId);
            return emailRepository.save(email);
        } else {
            throw new EmailNotAllowedToUpdateException("Email with id having state " + email.getState() + " is not allowed to update.");
        }

    }

    @Override
    public void delete(Long emailId) {
        if (emailRepository.existsById(emailId)) {
           emailRepository.deleteById(emailId);
        } else {
            throw new EmailNotFoundException("Email with id " + emailId + " not found");
        }
    }

    @Override
    public void deleteBulk(List<Long> emailIds) {
        if (!emailIds.isEmpty()) {
            emailRepository.deleteAllById(emailIds);
        } else {
            throw new EmailNotFoundException("Emails with id " + emailIds + " not found");
        }
    }

    @Override
    public List<Email> findAll() {
        return emailRepository.findAll();
    }

    @Override
    public Optional<Email> findById(Long id) {
        return emailRepository.findById(id);
    }

    @Override
    public List<Email> findAllByFrom(String from) {
        return emailRepository.findAllByFromContainingIgnoreCase(from);
    }
}
