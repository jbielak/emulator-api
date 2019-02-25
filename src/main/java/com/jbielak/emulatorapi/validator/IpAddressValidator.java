package com.jbielak.emulatorapi.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SupportedValidationTarget({ValidationTarget.PARAMETERS, ValidationTarget.ANNOTATED_ELEMENT})
public class IpAddressValidator implements ConstraintValidator<IpAddress, String> {

    @SuppressWarnings("PMD.UncommentedEmptyMethodBody")
    @Override
    public void initialize(IpAddress ipAddress) {
    }

    @Override
    public boolean isValid(String ipAddress, ConstraintValidatorContext cxt) {
        Pattern pattern =
                Pattern.compile("^([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})$");
        Matcher matcher = pattern.matcher(ipAddress);
        try {
            if (!matcher.matches()) {
                return false;
            } else {
                for (int i = 1; i <= 4; i++) {
                    int octet = Integer.valueOf(matcher.group(i));
                    if (octet > 255) {
                        return false;
                    }
                }
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
