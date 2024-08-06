package de.monir.example.emails.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmailNotFoundException  extends RuntimeException{
    String message;
    public EmailNotFoundException(String message) {
        super(message);
    }

    public EmailNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
