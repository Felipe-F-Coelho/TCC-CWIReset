package io.github.cwireset.tcc.validator;

import io.github.cwireset.tcc.domain.Pagamento;
import io.github.cwireset.tcc.domain.Periodo;
import io.github.cwireset.tcc.domain.StatusPagamento;
import io.github.cwireset.tcc.exceptions.PeriodoInvalidoException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
public class BasicValidatorReserva {

    public Periodo validarPeriodo(Periodo periodo) throws Exception {

        if(DAYS.between(periodo.getDataHoraInicial().withHour(0), periodo.getDataHoraFinal().withHour(0)) < 1 && DAYS.between(periodo.getDataHoraInicial(), periodo.getDataHoraFinal()) >= 0)
            throw new PeriodoInvalidoException("O número mínimo de diárias precisa ser maior ou igual à 1.");

        if(periodo.getDataHoraInicial().getHour() < 14)
            periodo.setDataHoraInicial(periodo.getDataHoraInicial().withHour(14));

        if(periodo.getDataHoraFinal().getHour() > 12)
            periodo.setDataHoraFinal(periodo.getDataHoraFinal().withHour(12));

        if(periodo.getDataHoraFinal().isBefore(periodo.getDataHoraInicial()))
            throw new PeriodoInvalidoException("A data final da reserva precisa ser maior do que a data inicial.");

        return periodo;
    }

    public Pagamento inicarPagamento(Periodo periodo, BigDecimal valorDiaria, Integer quantidadePessoas) throws Exception {

        BigDecimal daysBetween = new BigDecimal(DAYS.between(periodo.getDataHoraInicial(), periodo.getDataHoraFinal()));

        BigDecimal valorTotal = daysBetween.multiply(valorDiaria.multiply(BigDecimal.valueOf(quantidadePessoas)));

        return Pagamento.builder()
                .formaEscolhida(null)
                .status(StatusPagamento.PENDENTE)
                .valorTotal(valorTotal)
                .build();
    }

}
