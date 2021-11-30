package io.github.cwireset.tcc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PagamentoReservaNaoPendenteException extends Exception {
    public PagamentoReservaNaoPendenteException(String pagamento, String tipoStatus) {
        super(String.format("Não é possível realizar o %s para esta reserva, pois ela não está no status %s.",pagamento,tipoStatus));
    }
}
