package com.api.algafood.core.validation.jackson;

import com.api.algafood.api.model.mixin.CidadeMixin;
import com.api.algafood.api.model.mixin.CozinhaMixin;
import com.api.algafood.api.model.mixin.RestauranteMixin;
import com.api.algafood.domain.model.Cidade;
import com.api.algafood.domain.model.Cozinha;
import com.api.algafood.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        /*
         * Chamando o método da classe SimpleModule, eu digo para a aplicação que
         * a classe restaurante tem uma configuração de Jacksons em RestauranteMixin
         */
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
    }
}
