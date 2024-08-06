package de.monir.example.emails.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import de.monir.example.emails.model.EmailAddress;
import de.monir.example.emails.model.State;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class EmailUpdateDTO {
    @JsonAlias(value = "emailFrom")
    private String from;

    @JsonAlias(value = "emailTo")
    private Set<EmailAddress> emailTo = new HashSet<>();

    @JsonAlias(value = "emailCC")
    private Set<EmailAddress> emailCC = new HashSet<>();

    @JsonAlias("emailSubject")
    private String subject;

    @JsonAlias(value = "emailBody")
    private String body;

    private State state;
}
