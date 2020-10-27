package com.api.algafood.core.validation.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        /*
         * Chamando o método da classe SimpleModule, eu digo para a aplicação que
         * a classe restaurante tem uma configuração de Jacksons em RestauranteMixin
         */
//        setMixInAnnotation(Cidade.class, CidadeMixin.class);
//        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
    }
}
