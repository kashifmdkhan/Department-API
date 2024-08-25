package com.codingshuttle.kashif.week2Assignment.departmentAssignment.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValidationAnnotation,String> {
    @Override
    public boolean isValid(String inputPassword, ConstraintValidatorContext constraintValidatorContext) {
        boolean isUpperCaseInvolved = false;
        boolean isLowerCaseInvolved = false;
        boolean containsSpecialCharacter = false;
        boolean isLengthTen = false;

        if(inputPassword.length()<10)
            return false;
        else
            isLengthTen = true;

        for(int i = 0; i < inputPassword.length(); i++){
            if(inputPassword.charAt(i)>=97 && inputPassword.charAt(i)<=122)
                isLowerCaseInvolved = true;
            else if(inputPassword.charAt(i)>=65 && inputPassword.charAt(i)<=90)
                isUpperCaseInvolved = true;
            else if(inputPassword.charAt(i)>=33 && inputPassword.charAt(i)<=49)
                containsSpecialCharacter = true;
        }

        if(isLengthTen && isLowerCaseInvolved && isUpperCaseInvolved && containsSpecialCharacter)
            return true;
        return false;
    }
}
