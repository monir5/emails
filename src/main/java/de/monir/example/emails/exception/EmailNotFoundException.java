package de.monir.example.emails.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailNotFoundException  extends RuntimeException{
    private String message;
    public EmailNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public EmailNotFoundException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
}
