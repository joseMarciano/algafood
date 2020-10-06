package com.api.algafood.cozinha;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private Flyway flyway;

    @Before
    public void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(); // Colocando no setup o Log de validação
        RestAssured.port = port; // Passando o port para não repetir código
        RestAssured.basePath = "/cozinhas"; //Passando o basePath para não repetir código

        flyway.migrate();
    }

    @Test
    public void deveRetornarStatus200QuandoConsultarCozinhas() {
        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());

    }

    @Test
    public void deveConter4CozinhasQuandoConsultarCozinhas() {

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("", Matchers.hasSize(4)) // Verifica se o corpo tem 4 itens
                .body("nome", Matchers.hasItems("TAILANDESA", "INDIANA")); //Verifica se a propriedade nome existe TAILANDESA E INDIANA
    }

    @Test
    public void deveRetornarStatus201QuandoCadastrarCozinha(){
            RestAssured
                    .given()
                    .body("{ \"nome\": \"Chinesa\" }")
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .when()
                    .post()
                    .then()
                    .statusCode(HttpStatus.CREATED.value());
    }
}
