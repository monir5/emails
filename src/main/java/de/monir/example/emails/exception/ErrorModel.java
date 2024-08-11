package de.monir.example.emails.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorModel {
    private String errorId;
    private HttpStatus httpStatus;
    private String message;
    private String details;
    private LocalDateTime timestamp;

    public ErrorModel(HttpStatus httpStatus, String message, String details) {
        this(UUID.randomUUID().toString(),httpStatus, message, details, LocalDateTime.now());
    }
}