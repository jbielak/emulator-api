package com.jbielak.emulatorapi.validator;


import javax.validation.Constraint;
import javax.validation.ConstraintTarget;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IpAddressValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IpAddress {
    String message() default "Invalid IP address.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    ConstraintTarget validationAppliesTo() default ConstraintTarget.IMPLICIT;
}