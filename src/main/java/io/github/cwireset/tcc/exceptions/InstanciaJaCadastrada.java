package io.github.cwireset.tcc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InstanciaJaCadastrada extends Exception {
    public InstanciaJaCadastrada(String domain, String variavel, String argumento) {
        super(String.format("Já existe um recurso do tipo %s com %s com o valor '%s'.",domain,variavel,argumento));
    }
}
