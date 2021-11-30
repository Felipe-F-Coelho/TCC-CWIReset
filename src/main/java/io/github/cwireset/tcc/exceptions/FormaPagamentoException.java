package io.github.cwireset.tcc.exceptions;

import io.github.cwireset.tcc.domain.FormaPagamento;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FormaPagamentoException extends Exception {
    public FormaPagamentoException(FormaPagamento formaPagamento, List<FormaPagamento> formasAceitas) {
        super(String.format("O anúncio não aceita %s como forma de pagamento. As formas aceitas são %s.",formaPagamento,formasAceitas));
    }
}
