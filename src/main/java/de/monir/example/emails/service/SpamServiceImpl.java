package de.monir.example.emails.service;

import de.monir.example.emails.model.Email;
import de.monir.example.emails.model.State;
import de.monir.example.emails.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpamServiceImpl implements SpamService{

    static final Logger LOGGER = LoggerFactory.getLogger(SpamServiceImpl.class.getName());

    private final EmailRepository emailRepository;

    @Transactional
    @Scheduled(cron = "${cron.expression.dailyAt10}")
    @Override
    public void computeSpam() {
        List<Email> emails = emailRepository.findAllByFromContainingIgnoreCase(SpamService.SPAM_MARKED_EMAIL);
           emails.forEach(email -> {
               LOGGER.info(" Marking spam email {}", email);
               System.out.println(email.getFrom()+" , state "+ email.getState().getCode() + " is maked as spam.");
            email.setState(State.SPAM);
            emailRepository.save(email);
           });
    }
}
