package io.github.cwireset.tcc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NenhumaInstanciaEncontradaException extends Exception {
    public NenhumaInstanciaEncontradaException(String domain, String variavel, String argumento) {
        super(String.format("Nenhum(a) %s com %s com o valor '%s' foi encontrado.",domain,variavel,argumento));
    }
}
