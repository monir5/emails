package de.monir.example.emails.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Emails {

    @NotEmpty(message = "The list of emails should not be empty.")
    private List<Email> emails;
}
