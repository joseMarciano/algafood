package com.api.algafood.JPA.Restaurante;

import com.api.algafood.AlgafoodApplication;
import com.api.algafood.domain.model.Cozinha;
import com.api.algafood.domain.model.Restaurante;
import com.api.algafood.domain.repository.CozinhaRepository;
import com.api.algafood.domain.repository.RestauranteRepository;
import com.api.algafood.insfrastructure.repository.RestauranteRepositoryImpl;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaRestauranteMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new SpringApplicationBuilder(AlgafoodApplication.class)
                        .web(WebApplicationType.NONE).run(args);

        RestauranteRepository restauranteRepository = applicationContext
                .getBean(RestauranteRepositoryImpl.class);

        System.out.println("\r\n*********************************************************");
        System.out.println("*                   LISTA DE RESTURANTES                 *");
        System.out.println("*********************************************************");

        List<Restaurante> restaurantes = restauranteRepository.listar();
        restaurantes.forEach(restaurante -> {
            System.out.println("\r\n******************************************");
            System.out.println("*               NOME                     *");
            System.out.println(restaurante.getNome() + "-----" + restaurante.getCozinha().getNome());
        });

    }

}
