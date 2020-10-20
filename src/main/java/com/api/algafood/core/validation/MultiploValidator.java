package com.api.algafood.core.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

/*
 * Criando a classe com a lógica para fazer a validação da anotação @Multiplo
 * Deve implementar a ConstraintValidator, passando qual a anotação que será
 * validada e qual o tipo de objeto que será anotado com essa anotação
 * No meu caso coloquei um Number pois vamos validar o multiplo....
 * Em outros casos poderia ser String, ou qualquer outro tipo de objeto que vc queira validar
 */
public class MultiploValidator implements ConstraintValidator<Multiplo, Number> {

    private int numeroMultiplo;

    /*
     * Inicializa o validador para chamada do metodo isValid
     */
    @Override
    public void initialize(Multiplo constraintAnnotation) {
        this.numeroMultiplo = constraintAnnotation.numero();
    }

    /*
     *
     */
    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        Boolean valido = true;

        if (value != null) {
            var valorDecimal = BigDecimal.valueOf(value.doubleValue());
            var multiploDecimal = BigDecimal.valueOf(this.numeroMultiplo);
            var resto = valorDecimal.remainder(multiploDecimal); //retorna o resto

            valido = BigDecimal.ZERO.compareTo(resto) == 0; // Compara se ZERO é igual a resto

        }

        return valido;
    }
}
