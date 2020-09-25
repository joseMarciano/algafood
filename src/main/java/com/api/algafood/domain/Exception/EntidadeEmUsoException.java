package com.api.algafood.domain.Exception;

//@ResponseStatus(HttpStatus.CONFLICT) -- só usa se não quiser usar a ApiExceptionHandler
public class EntidadeEmUsoException extends NegocioException {

    public EntidadeEmUsoException(String mensagem) {
        super(mensagem);
    }
}
