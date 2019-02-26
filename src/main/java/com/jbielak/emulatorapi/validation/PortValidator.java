package com.jbielak.emulatorapi.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

@SupportedValidationTarget({ValidationTarget.PARAMETERS, ValidationTarget.ANNOTATED_ELEMENT})
public class PortValidator implements ConstraintValidator<Port, Integer> {

    @SuppressWarnings("PMD.UncommentedEmptyMethodBody")
    @Override
    public void initialize(Port port) {
    }

    @Override
    public boolean isValid(Integer port, ConstraintValidatorContext cxt) {
        return port >= 1025 && port <= 65536;
    }
}
