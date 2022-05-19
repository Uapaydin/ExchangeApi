package com.utku.exchange.util.validation;


import com.utku.exchange.service.ExchangeService;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Utku APAYDIN
 * @created 17/05/2022 - 15:12
 */
@Component
public class CurrencyValidator implements ConstraintValidator<Currency,String> {

    private final ExchangeService exchangeService;

    public CurrencyValidator(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return exchangeService.getAvailableSymbols().containsKey(value);
    }
}
