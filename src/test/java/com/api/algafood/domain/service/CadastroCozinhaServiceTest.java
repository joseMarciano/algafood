package com.api.algafood.domain.service;


import com.api.algafood.domain.Exception.EntidadeEmUsoException;
import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.model.Cozinha;
import com.api.algafood.domain.model.Restaurante;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CadastroCozinhaServiceTest {

    @Autowired
    private CozinhaService service;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private EntityManager manager;

    @Test
    public void deveCadastrarUmaCozinhaComSucesso(){
        //cenário
        var novaCozinha = new Cozinha();
        novaCozinha.setNome("Chinesa");

        //ação
        novaCozinha = service.save(novaCozinha);

        //validação
        assertThat(novaCozinha).isNotNull();
        assertThat(novaCozinha.getId()).isNotNull();
    }


    @Test(expected = ConstraintViolationException.class)
    public void deveFalharAoCadastrarCozinhaQuandoSemNome(){
        //cenário
        var novaCozinha = new Cozinha();

        //ação
        novaCozinha = service.save(novaCozinha);
        novaCozinha.setNome(null);

        //validação feita na anotação @Test
    }

    @Test(expected = EntidadeEmUsoException.class)
    public void deveFalharAoExcluirCozinhaEmUso(){
        //cenário
        var novaCozinha = new Cozinha();
        novaCozinha.setNome("Cozinha");
        var restaurante = new Restaurante();
        restaurante.setNome("Restaurante");
        restaurante.setTaxaFrete(BigDecimal.valueOf(23L));
        restaurante.setCozinha(novaCozinha);

        //ação
        novaCozinha = service.save(novaCozinha);
        restauranteService.save(restaurante);

        service.remove(novaCozinha.getId());

    }

    @Test(expected = EntidadeNaoEncontradaException.class)
    public void deveFalharAoExcluirCozinhaInexistente(){
        //cenário
        /*
         * Eu pego a lista de id de cozinhas, depois ordeno de modo ascendente e por ultimo
         * pego a função reduce e pego ultimo maior ID da lista, depois eu vou somar + 1
         * Com isso eu garanto que vou sempre tentar excluir uma cozinha inexistente
         */
        List resultList = manager.createQuery("SELECT c.id FROM Cozinha AS c").getResultList();
        Collections.sort(resultList);
        Long ultimoMaiorIdDaLista = (Long) resultList.stream().reduce((o, o2) -> o2).get();

        ultimoMaiorIdDaLista = ultimoMaiorIdDaLista + 1L;
        service.remove(ultimoMaiorIdDaLista);
    }
}
