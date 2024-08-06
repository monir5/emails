package de.monir.example.emails.service;

import de.monir.example.emails.dto.EmailUpdateDTO;
import de.monir.example.emails.exception.EmailNotFoundException;
import de.monir.example.emails.mapper.EmailMapper;
import de.monir.example.emails.model.Email;
import de.monir.example.emails.model.State;
import de.monir.example.emails.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

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
        if (emailRepository.existsById(emailId) && State.DRAFT.equals(emailUpdateDTO.getState())) {
            Email email = emailMapper.emailUpdateDTOToEmail(emailUpdateDTO);
            return emailRepository.save(email);
        }
        throw new EmailNotFoundException();
    }

    @Override
    public void delete(Long emailId) {
        if (emailRepository.existsById(emailId)) {
           emailRepository.deleteById(emailId);
        }
        throw new EmailNotFoundException();
    }

    @Override
    public void deleteBulk(List<Long> emailIds) {
        if (!emailIds.isEmpty()) {
            emailRepository.deleteAllById(emailIds);
        }
        throw new EmailNotFoundException();
    }
}
