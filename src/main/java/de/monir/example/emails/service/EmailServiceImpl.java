package de.monir.example.emails.service;

import de.monir.example.emails.dto.EmailUpdateDTO;
import de.monir.example.emails.exception.EmailNotFoundException;
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
            //TODO: Mapping should be done.
            Email email = new Email();
            email.setId(emailId);
            email.setState(State.DRAFT);
            email.setFrom(emailUpdateDTO.getFrom());
            email.setEmailTo(emailUpdateDTO.getEmailTo());
            email.setEmailCC(emailUpdateDTO.getEmailCC());
            email.setSubject(emailUpdateDTO.getSubject());
            email.setBody(emailUpdateDTO.getBody());
            email.setState(emailUpdateDTO.getState());
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
