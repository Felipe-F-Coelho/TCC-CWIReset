package io.github.cwireset.tcc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SolicitanteInvalidoAnuncianteException extends Exception {
    public SolicitanteInvalidoAnuncianteException() {
        super("O solicitante de uma reserva não pode ser o próprio anunciante.");
    }
}
