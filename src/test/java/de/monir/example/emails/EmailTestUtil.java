package de.monir.example.emails;

import de.monir.example.emails.dto.EmailUpdateDTO;
import de.monir.example.emails.model.Email;
import de.monir.example.emails.model.EmailAddress;
import de.monir.example.emails.model.State;

import java.util.Set;

public class EmailTestUtil {

    public static Email getDraftEmail() {
        Email email = getEmail();
        email.setState(State.DRAFT);
        return email;
    }

    public static Email getSentEmail() {
        Email email = getEmail();
        email.setState(State.SENT);
        return email;
    }

    public static Email getEmail() {
        return Email.builder()
                .id(1L)
                .from("email_from@example.com")
                .body("Email Body")
                .subject("Email Subject")
                .emailCC(getEmailCC())
                .emailTo(getEmailTo())
                .state(State.SENT)
                .build();
    }

    public static Set<EmailAddress> getEmailTo() {
        return Set.of(EmailAddress.builder()
                .email("email_to@example.com")
                .build());
    }

    public static Set<EmailAddress> getEmailCC() {
        return Set.of(EmailAddress.builder()
                .email("email_cc@example.com")
                .build());
    }

    public static EmailUpdateDTO getEmailUpdateDTO() {
        return EmailUpdateDTO.builder()
                .from("email_from@example.com")
                .body("Email Body")
                .subject("Email Subject")
                .emailCC(getEmailCC())
                .emailTo(getEmailTo())
                .state(State.DRAFT)
                .build();
    }
}
