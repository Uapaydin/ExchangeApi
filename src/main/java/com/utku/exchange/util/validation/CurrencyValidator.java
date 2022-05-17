package com.utku.exchange.util.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author tcuapaydin
 * @created 17/05/2022 - 15:12
 */
public class CurrencyValidator implements ConstraintValidator<Currency,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return true;
//        return exchangeService.getAvailableSymbols().containsKey(value);
    }
}
