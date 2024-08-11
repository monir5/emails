package de.monir.example.emails.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class.getName());

    /**
     * Global handler for Exception
     *
     * @param request HttpServletRequest
     * @param ex Exception
     * @return a ResponseEntity with HttpStatus Bad Request
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorModel> handleException(HttpServletRequest request, Exception ex) {
        ErrorModel error = new ErrorModel(HttpStatus.BAD_REQUEST, "Bad Request", ex.getMessage());
        LOGGER.error("InvalidEmailAddressException handler executed -> {} response - ID: {} ", error.getHttpStatus(), error.getErrorId(), ex);
        return ResponseEntity.status(error.getHttpStatus()).body(error);
    }

    /**
     * Global handler for EmailNotFoundException
     *
     * @param request HttpServletRequest
     * @param ex EmailNotFoundException
     * @return a ReponseEntity with HttpStatus Not Found
     */
    @ExceptionHandler(EmailNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorModel> handleEmailNotFoundException(HttpServletRequest request, RuntimeException ex) {
        ErrorModel error = new ErrorModel(HttpStatus.BAD_REQUEST, "Email Not Found", ex.getMessage());
        LOGGER.error("InvalidEmailAddressException handler executed -> {} response - ID: {} ", error.getHttpStatus(), error.getErrorId(), ex);
        return ResponseEntity.status(error.getHttpStatus()).body(error);
    }

    /**
     * Global handler for InvalidEmailAddressException.
     *
     * @param request HttpServletRequest
     * @param ex InvalidEmailAddressException
     * @return a ResponseEntity with HttpStatus Bad Request
     */
    @ExceptionHandler(InvalidEmailAddressException.class)
    public ResponseEntity handleInvalidEmailAdressException(HttpServletRequest request, InvalidEmailAddressException ex) {
        ErrorModel error = new ErrorModel(HttpStatus.BAD_REQUEST, "Invalid Email Found", ex.getMessage());
        LOGGER.error("InvalidEmailAddressException handler executed -> {} response - ID: {} ", error.getHttpStatus(), error.getErrorId(), ex);
        return ResponseEntity.status(error.getHttpStatus()).body(error);
    }

    /**
     * Global handler for MethodArgumentNotValidException.
     *
     * @param request HttpServletRequest
     * @param ex MethodArgumentNotValidException
     * @return a ResponseEntity with HttpStatus Bad Request
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException ex) {
        ErrorModel error = new ErrorModel(HttpStatus.BAD_REQUEST, "Menthod Arugment Not Valid", "");
        ex.getBindingResult().getAllErrors().forEach((err) -> {
            String fieldName = ((FieldError) err).getField();
            String errorMessage = err.getDefaultMessage();
            error.setDetails(error.getDetails() +" " + fieldName + ": " + errorMessage);
        });
        LOGGER.error("MethodArgumentNotValidException handler executed -> {} response - ID: {} ", error.getHttpStatus(), error.getErrorId(), ex);
        return ResponseEntity.status(error.getHttpStatus()).body(error);
    }

    /**
     * Global handler for TransactionSystemException.
     *
     * @param request HttpServletRequest
     * @param ex TransactionSystemException
     * @return a ResponseEntity with HttpStatus Bad Request
     */
    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity handleTransactionSystemException(HttpServletRequest request, TransactionSystemException ex) {
        ErrorModel error = new ErrorModel(HttpStatus.BAD_REQUEST, "JPA Transaction could not be committed", ex.getLocalizedMessage());
        LOGGER.error("MethodArgumentNotValidException handler executed -> {} response - ID: {} ", error.getHttpStatus(), error.getErrorId(), ex);
        return ResponseEntity.status(error.getHttpStatus()).body(error);
    }
}
