package de.monir.example.emails.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import de.monir.example.emails.model.EmailAddress;
import de.monir.example.emails.model.State;
import de.monir.example.emails.validator.StateSubsetValidate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class EmailUpdateDTO {

    @NotNull(message = "Email-From should not be null.")
    @NotEmpty(message = "Email-From should not be empty.")
    @Size(min = 10, max = 100, message = "Email length should have less than or equal to 100 characters.")
    @Email
    @JsonAlias(value = "emailFrom")
    private String from;

    @NotNull(message = "EmailTo should not be null.")
    @NotEmpty(message = "The list of email-To should not be empty.")
    @JsonAlias(value = "emailTo")
    private Set<EmailAddress> emailTo = new HashSet<>();

    @JsonAlias(value = "emailCC")
    private Set<EmailAddress> emailCC = new HashSet<>();

    @JsonAlias("emailSubject")
    @Size(max = 500, message = "Email subject should have less than or equal to 500 characters.")
    private String subject;

    @JsonAlias(value = "emailBody")
    @Size(max = 5000, message = "Email body should have less than or equal to 5000 characters.")
    private String body;

    @NotNull(message = "The state of email should not be null.")
    @StateSubsetValidate(anyOf = {State.DRAFT, State.SENT, State.DELETED, State.SPAM}, message = "The state of the email should be one of these four states DRAFT(0), SENT(1), DELETED(2) and SPAM(3).")
    private State state;
}
