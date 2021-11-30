package io.github.cwireset.tcc.mapper;

import io.github.cwireset.tcc.domain.Reserva;
import io.github.cwireset.tcc.response.InformacaoReservaResponse;

import java.util.function.Function;

public class InformacaoReservadaMapper implements Function<Reserva, InformacaoReservaResponse> {
    @Override
    public InformacaoReservaResponse apply(Reserva reserva) {
        return InformacaoReservaResponse.builder()
                .idReserva(reserva.getId())
                .anuncio(new AnuncioMapper().apply(reserva.getAnuncio()))
                .pagamento(reserva.getPagamento())
                .periodo(reserva.getPeriodo())
                .solicitante(new UsuarioMapper().apply(reserva.getSolicitante()))
                .quantidadePessoas(reserva.getQuantidadePessoas())
                .build();
    }
}
