package de.monir.example.emails.service;


import de.monir.example.emails.EmailTestUtil;
import de.monir.example.emails.dto.EmailUpdateDTO;
import de.monir.example.emails.exception.EmailNotAllowedToUpdateException;
import de.monir.example.emails.exception.EmailNotFoundException;
import de.monir.example.emails.mapper.EmailMapper;
import de.monir.example.emails.model.Email;
import de.monir.example.emails.repository.EmailRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class EmailServiceImplTest {
    @InjectMocks
    private EmailServiceImpl emailService;

    @Mock
    private EmailRepository emailRepository;

    @Mock
    private EmailMapper emailMapper;

    @Test
    public void update_email_success() throws Exception {
        // when
        Long emailId = 1L;
        Email email = EmailTestUtil.getDraftEmail();
        EmailUpdateDTO emailUpdateDTO = EmailTestUtil.getEmailUpdateDTO();
        Mockito.when(emailRepository.findById(emailId)).thenReturn(Optional.of(email));
        Mockito.when(emailMapper.emailUpdateDTOToEmail(emailUpdateDTO)).thenReturn(email);
        Mockito.when(emailRepository.save(Mockito.any(Email.class))).thenReturn(email);
        // call
        Email updatedEmail = emailService.update(emailId, emailUpdateDTO);
        // verify
        assertEquals(updatedEmail, email);
    }

    @Test
    public void update_email_failed_as_not_draft() throws Exception {
        Long emailId = 1L;
        Email email = EmailTestUtil.getSentEmail();
        EmailUpdateDTO emailUpdateDTO = EmailTestUtil.getEmailUpdateDTO();
        Mockito.when(emailRepository.findById(emailId)).thenReturn(Optional.of(email));

        // call and verify
        assertThrows(EmailNotAllowedToUpdateException.class, () -> emailService.update(emailId, emailUpdateDTO));
    }

    @Test
    public void update_email_failed_email_not_found() throws Exception {
        //when
        Long emailId = 1L;
        EmailUpdateDTO emailUpdateDTO = EmailTestUtil.getEmailUpdateDTO();
        Mockito.when(emailRepository.findById(emailId)).thenReturn(Optional.empty());
        // call and verify
        assertThrows(EmailNotFoundException.class, () -> emailService.update(emailId, emailUpdateDTO));
    }
}
