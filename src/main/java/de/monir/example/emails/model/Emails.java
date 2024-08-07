package de.monir.example.emails.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Emails {

    @NotEmpty(message = "The list of emails should not be empty.")
    private List<Email> emails;
}
