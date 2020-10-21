package com.api.algafood.api.assembler;

import com.api.algafood.api.model.representation.restaurante.RestauranteCompleta;
import com.api.algafood.api.model.representation.restaurante.RestauranteCompletaListagem;
import com.api.algafood.domain.model.Cozinha;
import com.api.algafood.domain.model.Restaurante;
import com.api.algafood.domain.repository.CozinhaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteAssemblers implements Converter<Restaurante, RestauranteCompletaListagem, RestauranteCompleta> {

    private ModelMapper modelMapper;
    private CozinhaRepository cozinhaRepository;

    public RestauranteAssemblers(ModelMapper modelMapper, CozinhaRepository cozinhaRepository) {
        this.modelMapper = modelMapper;
        this.cozinhaRepository = cozinhaRepository;
    }

    @Override
    public Restaurante toDomainObject(RestauranteCompleta restauranteCompleta) {
        /*Ajuste feito pois na hora de reotrnar o objeto, o nome da cozinha retornava null
        * Isso deve ser provisório
        */
        Cozinha cozinha = cozinhaRepository.findById(restauranteCompleta.getCozinha().getId()).get();
        Restaurante restaurante = modelMapper.map(restauranteCompleta, Restaurante.class);
        restaurante.setCozinha(cozinha);

        return restaurante;
    }

    @Override
    public RestauranteCompletaListagem toDTO(Restaurante restaurante) {
        return modelMapper.map(restaurante, RestauranteCompletaListagem.class);
    }

    @Override
    public List<RestauranteCompletaListagem> toCollectionDTO(List<Restaurante> restaurantes) {
        return restaurantes.stream().map(restaurante -> toDTO(restaurante))
                .collect(Collectors.toList());
    }


}
