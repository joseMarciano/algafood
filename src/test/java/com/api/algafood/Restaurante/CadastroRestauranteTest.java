package com.api.algafood.Restaurante;


import com.api.algafood.domain.model.Cozinha;
import com.api.algafood.domain.model.Restaurante;
import com.api.algafood.domain.repository.CozinhaRepository;
import com.api.algafood.domain.repository.restaurante.RestauranteRepository;
import com.api.algafood.util.DatabaseCleaner;
import com.api.algafood.util.JsonReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroRestauranteTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    private String jsonCorretoRestaurante;

    private Integer qntRestaurantesCadastrados;

    private Restaurante restauranteGuellere;


    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = this.port;
        RestAssured.basePath = "/restaurantes";

        this.jsonCorretoRestaurante =
                JsonReader.read("/json/Restaurante/cadastro-restaurante.json");

        databaseCleaner.clearTables();
        prepararDados();
    }

    @Test
    public void deveRetornarStatus200QuandoConsultarRestaurantes() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveRetornarQuantidadeDeRestaurantesCadastrados() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("", hasSize(this.qntRestaurantesCadastrados));
    }

    @Test
    public void deveRetornarStatus201QuandoCadastrarRestaurantes(){

        given()
                .body(this.jsonCorretoRestaurante)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deveRetornarRespostaEStatusCorretoQuandoConsultarRestauranteExistente(){

        given()
                .pathParam("restauranteId",this.restauranteGuellere.getId())
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .get("/{restauranteId}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome",equalTo("Guellere"),
                        "taxaFrete", notNullValue(),
                        "cozinha", notNullValue());


    }


    private void prepararDados() {
        this.restauranteGuellere = restauranteBuilderSave("Guellere");
        restauranteBuilderSave("Texugos");
        restauranteBuilderSave("Lions");

        this.qntRestaurantesCadastrados = (int) restauranteRepository.count();

    }

    private Restaurante restauranteBuilderSave(String nome) {
        var restauranteBuilder = new Restaurante();
        var cozinha = new Cozinha();
        cozinha.setNome("CozinhaTester");
        cozinhaRepository.save(cozinha);

        restauranteBuilder.setNome(nome);
        restauranteBuilder.setTaxaFrete(BigDecimal.valueOf(100.00));
        restauranteBuilder.setCozinha(cozinha);
        restauranteRepository.save(restauranteBuilder);

        return restauranteBuilder;
    }

}
