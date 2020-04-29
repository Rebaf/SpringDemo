package eu.additude.demo.model.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AgeValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Age {

    // Is verplicht maar niet gebruiken
    Class<?>[] groups() default {};

    // Is verplicht maar niet gebruiken
    Class<? extends Payload>[] payload() default {};

    String message();

    int min();

    int max();
}
