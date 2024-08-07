package de.monir.example.emails.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class EmailAddress {

    @Column(nullable = false)
    @NotNull
    @Size(min = 10, max = 255, message = "Email length should have less than or equal to 100 characters")
    @Email
    private String email;
}
