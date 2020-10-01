package com.api.algafood.core.validation;

import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.math.BigDecimal;

public class ValorZeroIncluirDescricaoValidator implements ConstraintValidator<ValorZeroIncluirDescricao, Object> {

    private String valorField;
    private String descricaoField;
    private String descricaoObrigatoria;


    @Override
    public void initialize(ValorZeroIncluirDescricao constraint) {
        this.valorField = constraint.valorField();
        this.descricaoField = constraint.descricaoField();
        this.descricaoObrigatoria = constraint.descricaoObrigatoria();
    }

    @Override
    public boolean isValid(Object objetoValidacao, ConstraintValidatorContext context) {
        Boolean valido = true;

        /*
         * BeansUtils --> Nesse caso é para pegar o metodo get dinamicamente já que
         * eu não sei que tipo de instancia estou recebendo no objetoValidacao
         */
        try {
            BigDecimal valor = (BigDecimal) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), this.valorField).getReadMethod().invoke(objetoValidacao);
            String descricao = (String) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), this.descricaoField).getReadMethod().invoke(objetoValidacao);

            if (valor != null && BigDecimal.ZERO.compareTo(valor) == 0 && descricao != null) {
                valido = descricao.toLowerCase().contains(this.descricaoObrigatoria.toLowerCase());
            }
            return valido;
        } catch (Exception e) {
            throw new ValidationException(e);
        }
    }
}
