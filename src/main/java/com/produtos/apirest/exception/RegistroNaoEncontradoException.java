package com.produtos.apirest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RegistroNaoEncontradoException extends RuntimeException {

    public RegistroNaoEncontradoException(){
        super();
    }

    public RegistroNaoEncontradoException(String message){
        super(message);
    }

    public RegistroNaoEncontradoException(Throwable cause){
        super(cause);
    }

    public RegistroNaoEncontradoException(String message, Throwable cause){
        super(message, cause);
    }
}
