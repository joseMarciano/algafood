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
        Restaurante restaurante = modelMapper.map(restauranteCompleta, Restaurante.class);
        restaurante.setCozinha(getCozinha(restauranteCompleta));

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

    @Override
    public void copyToDomainObject(RestauranteCompleta restauranteCompleta, Restaurante restaurante) {
        /* Para evitar Caused by: org.hibernate.HibernateException: identifier of an instance of
         * com.api.algafood.domain.model.Cozinha was altered from 1 to 2
         * Poderia ser resolvido assim: restaurante.setCozinha(new Cozinha()), mas como estou com problemas com o
         * retorno do nome da cozinha = null no json, encontrei essa maneira de contornar o problema
         */
        restaurante.setCozinha(getCozinha(restauranteCompleta));

        modelMapper.map(restauranteCompleta, restaurante);
    }


    private Cozinha getCozinha(RestauranteCompleta restauranteCompleta) {
        /*Ajuste feito pois na hora de retornar o objeto, o nome da cozinha retornava null
         * Isso deve ser provisório
         */
        return cozinhaRepository.findById(restauranteCompleta.getCozinha().getId()).get();
    }
}
