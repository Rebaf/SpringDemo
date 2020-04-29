package eu.additude.demo.model.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<Age, Integer> {

    int min;
    int max;

    @Override
    public void initialize(Age age) {
        this.min = age.min();
        this.max = age.max();
    }

    @Override
    public boolean isValid(Integer leeftijd, ConstraintValidatorContext ctx) {
        return leeftijd != null && (leeftijd >= min && leeftijd <= max);
    }
}
