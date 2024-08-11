package de.monir.example.emails.validator;

import de.monir.example.emails.model.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class StateSubsetValidator implements ConstraintValidator<StateSubsetValidate, State> {

    private State[] subset;
    private boolean required;

    @Override
    public void initialize(StateSubsetValidate constraint) {
        ConstraintValidator.super.initialize(constraint);
        this.subset = constraint.anyOf();
        this.required = constraint.required();
    }

    @Override
    public boolean isValid(State value, ConstraintValidatorContext context) {
        if (!this.required && value == null) {
            return true;
        }
        return Arrays.asList(subset).contains(value);
    }
}
