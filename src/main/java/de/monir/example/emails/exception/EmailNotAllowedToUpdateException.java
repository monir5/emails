package de.monir.example.emails.exception;

import org.springframework.lang.Nullable;

public class EmailNotAllowedToUpdateException extends RuntimeException {
    public EmailNotAllowedToUpdateException(@Nullable String message) {super(message);}
    public EmailNotAllowedToUpdateException(@Nullable String message, @Nullable Throwable cause) {super(message, cause);}
}
