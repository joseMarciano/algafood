package com.api.algafood.domain.service;


import com.api.algafood.domain.model.Cozinha;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CadastroCozinhaServiceTest {

    @Autowired
    private CozinhaService service;

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
}
