package com.api.algafood.api.assembler;

import com.api.algafood.api.model.representation.restaurante.RestauranteCompleta;
import com.api.algafood.api.model.representation.restaurante.RestauranteCompletaListagem;
import com.api.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteAssemblers implements Converter<Restaurante, RestauranteCompletaListagem, RestauranteCompleta> {

    private ModelMapper modelMapper;

    public RestauranteAssemblers(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Restaurante toDomainObject(RestauranteCompleta restauranteCompleta) {
        return modelMapper.map(restauranteCompleta, Restaurante.class);
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
