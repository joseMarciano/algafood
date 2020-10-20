package com.api.algafood.core.validation;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.PositiveOrZero;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/*
 * Criando uma anotação clone de @PositiveOrZero
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@PositiveOrZero
public @interface TaxaFrete {

    /*
     * Essa anotação indica que o atributo "message()" da anotação @PositiveOrZero
     * será sobreescrito pelo meu atributo message().
     * Se não fizermos isso, a mensagem default continuará sendo {javax.validation.constraints.PositiveOrZero.message}
     * ao invés de "{TaxaFrete.invalida}"
     */
    @OverridesAttribute(constraint = PositiveOrZero.class, name = "message")
    String message() default "{TaxaFrete.invalida}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
