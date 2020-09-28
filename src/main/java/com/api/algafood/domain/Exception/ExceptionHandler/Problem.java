package com.api.algafood.domain.Exception.ExceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
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
    private LocalDateTime timeStamp;
    private List<Field> fields;

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

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public List<Field> getFields() {
        return fields;
    }
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    /*Criação de uma classe Field para representar os campos
        inválidos para enviar como resposta no body
         */
    public static class Field{

        private String name;
        private String userMessage;

        public Field(String name, String userMessage) {
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
