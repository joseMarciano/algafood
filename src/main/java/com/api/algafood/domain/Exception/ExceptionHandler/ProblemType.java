package com.api.algafood.domain.Exception.ExceptionHandler;

public enum ProblemType {
    SYSTEM_ERROR("erro-interno", "Erro interno no servidor de aplicação"),
    RECURSO_NAO_ENCONTRADO("recurso-nao-encontrado", "Recurso não encontrado"),
    ERRO_NEGOCIO("erro-negocio", "Violação de regra de negócio"),
    ENTIDADE_EM_USO("entidade-em-uso", "Entidade em uso"),
    UNKNOWN_BODY("body-format-unknow", "Mensagem do corpo incompreensível"),
    INVALID_PROPERTY("invalid-property", "Propriedade inválida"),
    UNKNOWN_PROPERTY("unknown-property", "Propriedade não reconhecida"),
    INVALID_PARAMETER("invalid-parameter", "Parâmetro inválido"),
    INVALID_DATAS("invalid-datas", "Dados inválidos");

    private String title;
    private String url;

    ProblemType(String path, String title) {
        this.url = "http://localhost:8080/" + path;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
