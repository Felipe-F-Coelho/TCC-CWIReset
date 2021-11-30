package io.github.cwireset.tcc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ImpossibilityExclusion extends Exception {
    public ImpossibilityExclusion() {
        super("Não é possível excluir um imóvel que possua um anúncio.");
    }
}
