package com.api.algafood.api.ExceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.OffsetDateTime;
import java.util.List;

/*               RFC 7807
 *      Padronização de respostas de erro
 */
@JsonInclude(JsonInclude.Include.NON_NULL) // -- Só inclui na representação do problema se a propriedade != null
public class Problem {

    private Integer status;
    private String type;
    private String tittle;
    private String detail;

    /* Opcional
        private LocalDateTime dataHora;
        private String mensagem;
     */

    //Outros parametros opcionais... a parte da especificação
    private String userMessage;
    private OffsetDateTime timeStamp;
    private List<Objeto> objetos;

    public Problem(Integer status,
                   String type,
                   String tittle,
                   String detail) {
        this.status = status;
        this.type = type;
        this.tittle = tittle;
        this.detail = detail;
    }

    /*Criei um construtor apenas para adicionar o userMessage,
     não precisaria se eu estivesse utilizando o padrão Build com lombok
     */
    public Problem(Integer status,
                   String type,
                   String tittle,
                   String detail,
                   String userMessage) {
        this.status = status;
        this.type = type;
        this.tittle = tittle;
        this.detail = detail;
        this.userMessage = userMessage;
    }

    public Problem() {

    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public OffsetDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(OffsetDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<Objeto> getObjetos() {
        return objetos;
    }

    public void setObjetos(List<Objeto> objetos) {
        this.objetos = objetos;
    }

    /*Criação de uma classe Field para representar os campos
            inválidos para enviar como resposta no body
             */
    public static class Objeto {

        private String name;
        private String userMessage;

        public Objeto(String name, String userMessage) {
            this.name = name;
            this.userMessage = userMessage;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUserMessage() {
            return userMessage;
        }

        public void setUserMessage(String userMessage) {
            this.userMessage = userMessage;
        }
    }
}
