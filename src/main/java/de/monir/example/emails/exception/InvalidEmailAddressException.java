package de.monir.example.emails.exception;

import org.springframework.lang.Nullable;

public class InvalidEmailAddressException extends RuntimeException {
    public InvalidEmailAddressException(@Nullable String message) {super(message);}
    public InvalidEmailAddressException(@Nullable String message, @Nullable Throwable cause) {super(message, cause);}

}
