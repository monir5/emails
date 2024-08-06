package de.monir.example.emails.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;
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
public class Email {

    @JsonAlias(value = "emailId")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "email_id_sequence")
    @SequenceGenerator(name = "email_id_sequence", sequenceName = "email_id_sequence", allocationSize = 1)
    private long id;

    @JsonAlias(value = "emailFrom")
    @Column(name = "\"from\"", nullable = false)
    private String from;

    @JsonAlias(value = "emailTo")
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "email_address_to", joinColumns = @JoinColumn(name = "email_id"))
    private Set<EmailAddress> emailTo = new HashSet<>();

    @JsonAlias(value = "emailCC")
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "email_address_cc", joinColumns = @JoinColumn(name = "email_id"))
    private Set<EmailAddress> emailCC = new HashSet<>();

    @JsonAlias("emailSubject")
    private String subject;

    @JsonAlias(value = "emailBody")
    @Column(nullable = false)
    private String body;

    @Column(name = "\"state\"", nullable = false)
    private State state;
}





