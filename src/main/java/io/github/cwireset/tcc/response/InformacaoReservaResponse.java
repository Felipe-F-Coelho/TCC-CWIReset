package io.github.cwireset.tcc.response;

import io.github.cwireset.tcc.domain.Pagamento;
import io.github.cwireset.tcc.domain.Periodo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class InformacaoReservaResponse {

    private long idReserva;
    private DadosSolicitanteResponse solicitante;
    private Integer quantidadePessoas;
    private DadosAnuncioResponse anuncio;
    private Periodo periodo;
    private Pagamento pagamento;

}
