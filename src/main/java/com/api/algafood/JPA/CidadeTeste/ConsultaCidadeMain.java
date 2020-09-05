package com.api.algafood.JPA.CidadeTeste;

import com.api.algafood.AlgafoodApplication;
import com.api.algafood.domain.model.Cidade;
import com.api.algafood.domain.model.Cozinha;
import com.api.algafood.domain.repository.CidadeRepository;
import com.api.algafood.insfrastructure.repository.CidadeRepositoryImpl;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaCidadeMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new SpringApplicationBuilder(AlgafoodApplication.class)
                        .web(WebApplicationType.NONE).run(args);


        CidadeRepository cidadeRepository = applicationContext
                .getBean(CidadeRepositoryImpl.class);

        List<Cidade> cidades = cidadeRepository.listar();
        cidades.forEach(cidade -> System.out.println(cidade.getNome() + "---" + cidade.getEstado().getNome()));

    }

}
