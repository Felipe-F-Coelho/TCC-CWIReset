package io.github.cwireset.tcc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PeriodoJaReservado extends Exception {
    public PeriodoJaReservado() {
        super("Este anuncio já esta reservado para o período informado.");
    }
}
