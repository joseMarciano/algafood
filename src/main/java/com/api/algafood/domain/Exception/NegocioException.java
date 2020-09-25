package com.api.algafood.domain.Exception;

//@ResponseStatus(HttpStatus.BAD_REQUEST) -- só usa se não quiser usar a ApiExceptionHandler
public class NegocioException extends RuntimeException {

    public NegocioException(String message) {
        super(message);
    }

    public NegocioException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }

}
