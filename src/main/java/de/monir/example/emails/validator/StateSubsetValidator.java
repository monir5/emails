package de.monir.example.emails.validator;

import de.monir.example.emails.model.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class StateSubsetValidator implements ConstraintValidator<StateSubset, State> {

    private State[] subset;

    @Override
    public void initialize(StateSubset constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(State value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subset).contains(value);
    }
}
