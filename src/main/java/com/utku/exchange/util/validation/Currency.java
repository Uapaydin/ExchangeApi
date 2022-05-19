package com.utku.exchange.util.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * @author Utku APAYDIN
 * @created 17/05/2022 - 15:33
 */
@Constraint(validatedBy = CurrencyValidator.class)
@Target({TYPE,FIELD,ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Currency {
    String message() default "{com.utku.exchange.util.validation.Currency.message}";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};
}
