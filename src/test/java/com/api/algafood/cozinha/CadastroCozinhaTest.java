package com.api.algafood.cozinha;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaTest {

    /*
     * Injetando o RAMDOM_PORT... pois o spring vai iniciar o tomCat numa porta aleatória
     * Desse jeito eu consigo pegar a porta e jogar dentro de .port();
     */
    @LocalServerPort
    private int port;

    @Test
    public void deveRetornarStatus200QuandoConsultarCozinhas(){
        //usando a biblioteca restAssured

        /*
         * Habilitando os logs da lib quando o test falha
         */
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        /* Para um path = "/cozinhas", na porta 8080, aceitando retorno JSON...
         * Quando eu der um get.... espero o statusCode 200
         */

        RestAssured.given()
                .basePath("/cozinhas")
                .port(port)
                .accept(ContentType.JSON)
             .when()
                .get()
             .then()
                .statusCode(HttpStatus.OK.value());

    }
}
