package io.github.cwireset.tcc.exceptions;

import io.github.cwireset.tcc.domain.TipoImovel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReservaPessoasMinimasException extends Exception {
    public ReservaPessoasMinimasException(TipoImovel tipoImovel, String variavel) {
        super(String.format("Não é possivel realizar uma reserva com menos de %s para imóveis do tipo %s",variavel,tipoImovel));
    }
}
