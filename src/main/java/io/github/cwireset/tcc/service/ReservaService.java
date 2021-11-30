package io.github.cwireset.tcc.service;

import io.github.cwireset.tcc.domain.*;
import io.github.cwireset.tcc.exceptions.*;
import io.github.cwireset.tcc.mapper.InformacaoReservadaMapper;
import io.github.cwireset.tcc.repository.ReservaRepository;
import io.github.cwireset.tcc.request.CadastrarReservaRequest;
import io.github.cwireset.tcc.response.InformacaoReservaResponse;
import io.github.cwireset.tcc.validator.BasicValidatorReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class ReservaService {

    @Autowired
    ReservaRepository repository;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    AnuncioService anuncioService;

    public InformacaoReservaResponse cadastrarReserva(CadastrarReservaRequest cadastrarReservaRequest) throws Exception {

        BasicValidatorReserva validatorReserva = new BasicValidatorReserva();

        Usuario usuario = usuarioService.buscarIdUsuario(cadastrarReservaRequest.getIdSolicitante());

        Anuncio anuncio = anuncioService.buscarIdAnuncio(cadastrarReservaRequest.getIdAnuncio());

        Periodo periodo = validatorReserva.validarPeriodo(cadastrarReservaRequest.getPeriodo());

        if(anuncio.getAnunciante().equals(usuario))
            throw new SolicitanteInvalidoAnuncianteException();

        if(anuncio.getImovel().getTipoImovel().equals(TipoImovel.HOTEL) && cadastrarReservaRequest.getQuantidadePessoas() < 2)
            throw new ReservaPessoasMinimasException(TipoImovel.HOTEL, "2 pessoas");

        if(anuncio.getImovel().getTipoImovel().equals(TipoImovel.POUSADA) && DAYS.between(cadastrarReservaRequest.getPeriodo().getDataHoraInicial(), cadastrarReservaRequest.getPeriodo().getDataHoraFinal()) < 5)
            throw new ReservaPessoasMinimasException(TipoImovel.POUSADA, "5 diárias");

        Pagamento pagamento = validatorReserva.inicarPagamento(periodo, anuncio.getValorDiaria(), cadastrarReservaRequest.getQuantidadePessoas());

        if(repository.identificadorDePeriodo(anuncio.getId(),StatusPagamento.CANCELADO,StatusPagamento.ESTORNADO,periodo.getDataHoraInicial(),periodo.getDataHoraFinal(),periodo.getDataHoraInicial(),periodo.getDataHoraFinal()))
            throw new PeriodoJaReservado();

        Reserva reserva = Reserva.builder()
                .solicitante(usuario)
                .anuncio(anuncio)
                .periodo(periodo)
                .quantidadePessoas(cadastrarReservaRequest.getQuantidadePessoas())
                .dataHoraReserva(LocalDateTime.now())
                .pagamento(pagamento)
                .build();

        repository.save(reserva);

        return new InformacaoReservadaMapper().apply(reserva);
    }

    public Page<Reserva> listarReservasSolicitante(LocalDateTime dataHoraInicial, LocalDateTime dataHoraFinal, long idSolicitante, Pageable pageable) {
        if(dataHoraInicial == null || dataHoraFinal == null)
            return repository.findBySolicitanteId(idSolicitante,pageable);

        else {
            return repository.findBySolicitanteIdAndPeriodoDataHoraInicialBetweenAndPeriodoDataHoraFinalBetween(idSolicitante,dataHoraInicial,dataHoraFinal,dataHoraInicial,dataHoraFinal,pageable);
        }
    }

    public Page<Reserva> listarReservasAnunciante(long idAnunciante, Pageable pageable) {
        return repository.findAllByAnuncioAnuncianteId(idAnunciante,pageable);
    }

    public void pagarReserva(long idReserva, FormaPagamento formaPagamento) throws Exception {
        Reserva reserva = repository.findById(idReserva).orElseThrow(() ->
                new NenhumaInstanciaEncontradaException(TipoException.RESERVA.getSingular(),TipoException.ID.getSingular(),Long.toString(idReserva)));

        if(reserva.getAnuncio().getFormasAceitas()
                .stream()
                .noneMatch(formaPagamento1 -> formaPagamento1.equals(formaPagamento)))
            throw new FormaPagamentoException(formaPagamento,reserva.getAnuncio().getFormasAceitas());

        if(reserva.getPagamento().getStatus().equals(StatusPagamento.PAGO) ||
                reserva.getPagamento().getStatus().equals(StatusPagamento.CANCELADO) ||
                reserva.getPagamento().getStatus().equals(StatusPagamento.ESTORNADO))
            throw new PagamentoReservaNaoPendenteException("pagamento","PENDENTE");

        reserva.getPagamento().setFormaEscolhida(formaPagamento);
        reserva.getPagamento().setStatus(StatusPagamento.PAGO);

        repository.save(reserva);
    }

    public void cancelarReserva(long idReserva) throws Exception {
        Reserva reserva = repository.findById(idReserva).orElseThrow(() ->
                new NenhumaInstanciaEncontradaException(TipoException.RESERVA.getSingular(),TipoException.ID.getSingular(),Long.toString(idReserva)));

        if(reserva.getPagamento().getStatus().equals(StatusPagamento.PAGO) ||
                reserva.getPagamento().getStatus().equals(StatusPagamento.CANCELADO) ||
                reserva.getPagamento().getStatus().equals(StatusPagamento.ESTORNADO))
            throw new PagamentoReservaNaoPendenteException("cancelamento","PENDENTE");


        reserva.getPagamento().setStatus(StatusPagamento.CANCELADO);
        repository.save(reserva);
    }

    public void estornarReserva(long idReserva) throws Exception{
        Reserva reserva = repository.findById(idReserva).orElseThrow(() ->
                new NenhumaInstanciaEncontradaException(TipoException.RESERVA.getSingular(),TipoException.ID.getSingular(),Long.toString(idReserva)));

        if(reserva.getPagamento().getStatus().equals(StatusPagamento.PENDENTE) ||
                reserva.getPagamento().getStatus().equals(StatusPagamento.CANCELADO) ||
                reserva.getPagamento().getStatus().equals(StatusPagamento.ESTORNADO))
            throw new PagamentoReservaNaoPendenteException("estorno","PAGO");

        reserva.getPagamento().setStatus(StatusPagamento.ESTORNADO);
        reserva.getPagamento().setFormaEscolhida(null);

        repository.save(reserva);
    }
}
