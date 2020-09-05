package com.api.algafood.JPA.Cozinha;

import com.api.algafood.AlgafoodApplication;
import com.api.algafood.domain.model.Cozinha;
import com.api.algafood.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class InclusaoCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new SpringApplicationBuilder(AlgafoodApplication.class)
                        .web(WebApplicationType.NONE).run(args);

        CozinhaRepository cadastroCozinha = applicationContext.getBean(CozinhaRepository.class);

        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Brasileira");

        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Brasileira");

        System.out.println(cadastroCozinha.salvar(cozinha1));
        System.out.println(cadastroCozinha.salvar(cozinha2));

        System.out.println("*********************************************************");
        System.out.println("*                      TRANSAÇÃO 1   OK                 *");
        System.out.println("*********************************************************");

        System.out.println("*********************************************************");
        System.out.println("*                      TRANSAÇÃO 2   OK                 *");
        System.out.println("*********************************************************");
    }

}
