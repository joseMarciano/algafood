package com.api.algafood.JPA.Cozinha;

import com.api.algafood.AlgafoodApplication;
import com.api.algafood.domain.model.Cozinha;
import com.api.algafood.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaCozinhaByIdMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new SpringApplicationBuilder(AlgafoodApplication.class)
                .web(WebApplicationType.NONE).run(args);

        CozinhaRepository cadastroCozinha = applicationContext.getBean(CozinhaRepository.class);

        Cozinha cozinha = cadastroCozinha.find(1L);

        System.out.println("*********************************************************");
        System.out.println("*            COZINHA TRAZIDA DO BANCO PELO ID           *");
        System.out.println("*********************************************************");
        System.out.println(cozinha.getNome());
        System.out.println("*********************************************************");
    }

}
