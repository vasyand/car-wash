package ru.lieague.carwash.validation.annotation;

import ru.lieague.carwash.validation.validator.StatusAfterActiveValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = StatusAfterActiveValidator.class)
public @interface StatusAfterActive {

        String message() default "Status is not equal to Canceled or Completed";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

}
