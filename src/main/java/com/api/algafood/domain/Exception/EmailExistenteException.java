package com.api.algafood.domain.Exception;

public class EmailExistenteException extends NegocioException {

    public EmailExistenteException(String message) {
        super(message);
    }
}
