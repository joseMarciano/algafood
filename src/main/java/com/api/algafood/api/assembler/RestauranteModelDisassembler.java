package com.api.algafood.api.assembler;

import com.api.algafood.api.model.representation.restaurante.RestauranteCompleta;
import com.api.algafood.domain.model.Cozinha;
import com.api.algafood.domain.model.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class RestauranteModelDisassembler {

    public Restaurante toDomainObject(RestauranteCompleta restauranteCompleta){
        var restaurante = new Restaurante();
        restaurante.setNome(restauranteCompleta.getNome());
        restaurante.setTaxaFrete(restauranteCompleta.getTaxaFrete());
        var cozinha = new Cozinha();
        cozinha.setId(restauranteCompleta.getCozinha().getId());
        restaurante.setCozinha(cozinha);
        return restaurante;
    }

}
