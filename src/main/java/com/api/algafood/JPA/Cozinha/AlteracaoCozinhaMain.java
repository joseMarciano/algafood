package com.api.algafood.JPA.Cozinha;

import com.api.algafood.AlgafoodApplication;
import com.api.algafood.domain.model.Cozinha;
import com.api.algafood.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class AlteracaoCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new SpringApplicationBuilder(AlgafoodApplication.class)
                        .web(WebApplicationType.NONE).run(args);

        CozinhaRepository cadastroCozinha = applicationContext.getBean(CozinhaRepository.class);

        System.out.println("*********************************************************");
        System.out.println("*                                                       *");
        System.out.println("*                                                       *");
        System.out.println("*            ESTA CLASSE EDITA UMA COZINHA              *");
        System.out.println("*                                                       *");
        System.out.println("*                                                       *");
        System.out.println("*********************************************************");


        Cozinha cozinha = new Cozinha();
        cozinha.setId(1L);
        cozinha.setNome("BrasileiraBemLoco");

        cadastroCozinha.salvar(cozinha);
    }

}
