package com.api.algafood.JPA.FormaPagamentoTestes;

import com.api.algafood.AlgafoodApplication;
import com.api.algafood.domain.model.FormaPagamento;
import com.api.algafood.domain.repository.FormaPagamentoRepository;
import com.api.algafood.insfrastructure.repository.FormaPagamentoImpl;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaFormaPagamentoMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new SpringApplicationBuilder(AlgafoodApplication.class)
                        .web(WebApplicationType.NONE).run(args);

        FormaPagamentoRepository formaPagamentoRepository = applicationContext
                .getBean(FormaPagamentoImpl.class);

        List<FormaPagamento> pagamentos = formaPagamentoRepository.listar();
        pagamentos.forEach(formaPagamento -> System.out.println(formaPagamento.getDescricao()));


    }

}
