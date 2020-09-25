package com.api.algafood.domain.Exception;

//@ResponseStatus(HttpStatus.NOT_FOUND) -- só usa se não quiser usar a ApiExceptionHandler
public class EntidadeNaoEncontradaException extends NegocioException {

    public EntidadeNaoEncontradaException(String message){
        super(message);
    }
}
