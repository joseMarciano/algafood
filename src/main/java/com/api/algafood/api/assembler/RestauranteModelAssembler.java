package com.api.algafood.api.assembler;

import com.api.algafood.api.model.CozinhaDTO;
import com.api.algafood.api.model.representation.restaurante.RestauranteCompletaListagem;
import com.api.algafood.domain.model.Restaurante;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteModelAssembler {

    public RestauranteCompletaListagem toDTO(Restaurante restaurante) {
        var cozinhaDTO = new CozinhaDTO();
        cozinhaDTO.setId(restaurante.getCozinha().getId());
        cozinhaDTO.setNome(restaurante.getCozinha().getNome());

        var restauranteDTO = new RestauranteCompletaListagem();
        restauranteDTO.setId(restaurante.getId());
        restauranteDTO.setNome(restaurante.getNome());
        restauranteDTO.setTaxaFrete(restaurante.getTaxaFrete());
        restauranteDTO.setCozinha(cozinhaDTO);
        return restauranteDTO;
    }

    public List<RestauranteCompletaListagem> toCollectionDTO (List<Restaurante> restaurantes){
        return restaurantes.stream().map(restaurante -> toDTO(restaurante))
                .collect(Collectors.toList());
    }


}