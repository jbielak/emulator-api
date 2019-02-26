package com.jbielak.emulatorapi.validation;

import javax.validation.Constraint;
import javax.validation.ConstraintTarget;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PortValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Port {
    String message() default "Invalid port number. Valid port number range: 1025 - 65536.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    ConstraintTarget validationAppliesTo() default ConstraintTarget.IMPLICIT;
}