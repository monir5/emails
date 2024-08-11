package de.monir.example.emails.validator;

import de.monir.example.emails.model.EmailAddress;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.hibernate.validator.internal.metadata.descriptor.ConstraintDescriptorImpl;

import java.util.Set;
import java.util.regex.Pattern;

public class SetOfEmailAddressValidator implements ConstraintValidator<EmailAddressSetValidate, Set<EmailAddress>> {
    public static final String EMAIL_ADDRESS_REGEX="^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$";

    private boolean required;

    @Override
    public void initialize(EmailAddressSetValidate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(Set<EmailAddress> value, ConstraintValidatorContext context) {
        //boolean required = ((EmailAddressSetValidate)((ConstraintDescriptorImpl) ((ConstraintValidatorContextImpl) context).getConstraintDescriptor()).getAnnotation()).required();
        if (!this.required && value.isEmpty()) {
            return true;
        }
        return value.stream().anyMatch(this::validateEmailAddress);
    }

    private boolean validateEmailAddress(EmailAddress emailAddress) {
        return Pattern.compile(SetOfEmailAddressValidator.EMAIL_ADDRESS_REGEX).matcher(emailAddress.getEmail()).matches();
    }
}