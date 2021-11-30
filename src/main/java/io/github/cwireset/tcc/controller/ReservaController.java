package io.github.cwireset.tcc.controller;

import io.github.cwireset.tcc.domain.FormaPagamento;
import io.github.cwireset.tcc.domain.Periodo;
import io.github.cwireset.tcc.domain.Reserva;
import io.github.cwireset.tcc.request.CadastrarReservaRequest;
import io.github.cwireset.tcc.response.InformacaoReservaResponse;
import io.github.cwireset.tcc.service.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InformacaoReservaResponse cadastrarReserva(@RequestBody @Valid CadastrarReservaRequest cadastrarReservaRequest) throws Exception {
        return reservaService.cadastrarReserva(cadastrarReservaRequest);
    }

    @PutMapping("/{idReserva}/pagamentos")
    public void pagarReserva(@PathVariable @Valid long idReserva,@RequestBody @Valid FormaPagamento formaPagamento) throws Exception {
        reservaService.pagarReserva(idReserva,formaPagamento);
    }

    @GetMapping("/solicitantes/{idSolicitante}")
    public Page<Reserva> listarReservasSolicitante(@Valid Periodo periodo,
                                                   @PathVariable long idSolicitante,
                                                   @PageableDefault(sort = "periodoDataHoraFinal", direction = Sort.Direction.DESC) @ApiIgnore Pageable pageable) {
        return reservaService.listarReservasSolicitante(periodo.getDataHoraInicial(),periodo.getDataHoraFinal(),idSolicitante,pageable);
    }

    @GetMapping("/anuncios/anunciantes/{idAnunciante}")
    public Page<Reserva> listarReservasAnunciante(@PathVariable long idAnunciante,
                                               @PageableDefault(sort = "periodoDataHoraFinal", direction = Sort.Direction.DESC) @ApiIgnore Pageable pageable){
        return reservaService.listarReservasAnunciante(idAnunciante,pageable);
    }

    @PutMapping("/{idReserva}/pagamentos/cancelar")
    public void cancelarReserva(@PathVariable long idReserva) throws Exception {
        reservaService.cancelarReserva(idReserva);
    }

    @PutMapping("/{idReserva}/pagamentos/estornar")
    public void estornarReserva(@PathVariable long idReserva) throws Exception{
        reservaService.estornarReserva(idReserva);
    }

}