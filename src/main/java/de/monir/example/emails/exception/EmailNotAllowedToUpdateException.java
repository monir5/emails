package de.monir.example.emails.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailNotAllowedToUpdateException extends RuntimeException {
    private String message;

    public EmailNotAllowedToUpdateException(String message) {
        super(message);
        this.message = message;
    }

    public EmailNotAllowedToUpdateException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
}
