package com.codingshuttle.kashif.week2Assignment.departmentAssignment.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = {PasswordValidator.class})
public @interface PasswordValidationAnnotation {
    String message() default "a. contains one uppercase letter\n" +
            "b. contains one lowercase letter\n" +
            "c. contains one special character\n" +
            "d. minimum length is 10 characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
