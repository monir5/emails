package de.monir.example.emails.exception;

import org.springframework.lang.Nullable;

public class EmailNotFoundException  extends RuntimeException{
    public EmailNotFoundException(@Nullable String message) {super(message);}
    public EmailNotFoundException(@Nullable String message, @Nullable Throwable cause) { super(message, cause); }
}
