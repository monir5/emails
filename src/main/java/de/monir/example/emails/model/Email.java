package de.monir.example.emails.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import de.monir.example.emails.validator.StateSubset;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "emails")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Email {

    @JsonAlias(value = "emailId")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "email_id_sequence")
    @SequenceGenerator(name = "email_id_sequence", sequenceName = "email_id_sequence", allocationSize = 1)
    private long id;

    @JsonAlias(value = "emailFrom")
    @NotNull(message = "Email-From should not be null.")
    @NotEmpty(message = "Email-From should not be empty.")
    @Size(min = 10, max = 100, message = "Email length should have less than or equal to 100 characters.")
    @Column(name = "\"from\"", nullable = false)
    private String from;

    @NotNull(message = "EmailTo should not be null.")
    @NotEmpty(message = "The list of email-To should not be empty.")
    @JsonAlias(value = "emailTo")
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "email_address_to", joinColumns = @JoinColumn(name = "email_id"))
    private Set<EmailAddress> emailTo = new HashSet<>();

    @JsonAlias(value = "emailCC")
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "email_address_cc", joinColumns = @JoinColumn(name = "email_id"))
    private Set<EmailAddress> emailCC = new HashSet<>();

    @JsonAlias("emailSubject")
    @Size(max = 500, message = "Email subject should have less than or equal to 500 characters.")
    private String subject;

    @JsonAlias(value = "emailBody")
    @Size(max = 5000, message = "Email body should have less than or equal to 5000 characters.")
    @Column(nullable = false)
    private String body;

    @NotNull(message = "The state of email should not be null.")
    @StateSubset(anyOf = {State.DRAFT, State.SENT, State.DELETED, State.SPAM}, message = "The state of the email should be one of these four states DRAFT(0), SENT(1), DELETED(2) and SPAM(3).")
    @Column(name = "\"state\"", nullable = false)
    private State state;
}





