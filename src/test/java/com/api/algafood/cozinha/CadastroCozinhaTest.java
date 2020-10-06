package com.api.algafood.cozinha;

import com.api.algafood.domain.model.Cozinha;
import com.api.algafood.domain.repository.CozinhaRepository;
import com.api.algafood.util.DatabaseCleaner;
import com.api.algafood.util.JsonReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaTest {

    private static final Integer COZINHA_ID_INEXISTENTE = 9999999;

    /*
     * Injetando o RAMDOM_PORT... pois o spring vai iniciar o tomCat numa porta aleatória
     * Desse jeito eu consigo pegar a porta e jogar dentro de .port();
     */
    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CozinhaRepository repository;

    private Integer qntCozinhasCadastradas;
    private Cozinha cozinhaAmericana;
    private String jsonCorretoCozinhaChinesa;

    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(); // Colocando no setup o Log de validação
        RestAssured.port = port; // Passando o port para não repetir código
        RestAssured.basePath = "/cozinhas"; //Passando o basePath para não repetir código

        this.jsonCorretoCozinhaChinesa = JsonReader.read("/json/Cozinha/cadastroCozinha.json");

        databaseCleaner.clearTables();
        prepararDados();
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
    public void deveConterQuantidadeDeCozinhasCadastradasQuandoConsultarCozinhas() {

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("", Matchers.hasSize(this.qntCozinhasCadastradas)); // Verifica se o corpo tem 2 itens
    }

    @Test
    public void deveRetornarStatus201QuandoCadastrarCozinha() {


        RestAssured
                .given()
                .body(this.jsonCorretoCozinhaChinesa)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deveRetornarRespostaEStatusCorretosQuandoConsultarCozinhaExistente() {
        /*
         * Dado um pathParam com nome cozinhaId = 2, aceitando JSON na resposta, quando der um get em cozinhaId
         * então, espero um statusCode de OK(200) e no corpo um "nome" igual a "Brasileira"
         */
        RestAssured
                .given()
                .pathParam("cozinhaId", this.cozinhaAmericana.getId()) // parametro da requisição com o valor de 2
                .accept(ContentType.JSON)
                .when()
                .get("/{cozinhaId}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", Matchers.equalTo(this.cozinhaAmericana.getNome()));
    }

    @Test
    public void deveRetornarRespostaEStatus404QuandoConsultarCozinhaExistente() {
        /*
         * Dado um pathParam com nome cozinhaId = 100, aceitando JSON na resposta, quando der um get em cozinhaId
         * então, espero um statusCode de NOTfOUND(404)
         */
        RestAssured
                .given()
                .pathParam("cozinhaId", COZINHA_ID_INEXISTENTE) // parametro da requisição com o valor de 999999999
                .accept(ContentType.JSON)
                .when()
                .get("/{cozinhaId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepararDados() {
        this.cozinhaAmericana = cozinhaBuilderSave("Americana");
        cozinhaBuilderSave("Tailandesa");
        cozinhaBuilderSave("Brasileira");
        cozinhaBuilderSave("Coreana");

        this.qntCozinhasCadastradas = (int) repository.count(); // pega o numero de cozinhas cadastradas no db
    }

    private Cozinha cozinhaBuilderSave(String nome) {
        var cozinhaBuilder = new Cozinha();
        cozinhaBuilder.setNome(nome);
        repository.save(cozinhaBuilder);
        return cozinhaBuilder;
    }
}
