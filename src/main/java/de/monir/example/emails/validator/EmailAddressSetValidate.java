package de.monir.example.emails.validator;

import de.monir.example.emails.model.EmailAddress;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SetOfEmailAddressValidator.class)
@Documented
public @interface EmailAddressSetValidate {

    boolean required() default true;
    String message() default "Please enter a valid email address";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
