package de.monir.example.emails.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
    @Size(min = 6, max = 100, message = "Email length should have be in between 6-100 characters")
    private String email;
}
