package io.github.cwireset.tcc.repository;

import io.github.cwireset.tcc.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    Page<Reserva> findBySolicitanteId(long idSolicitante, Pageable pageable);

    Page<Reserva> findBySolicitanteIdAndPeriodoDataHoraInicialBetweenAndPeriodoDataHoraFinalBetween(long idSolicitante, LocalDateTime dataHoraInicial, LocalDateTime dataHoraFinal,LocalDateTime dataHoraInicial2, LocalDateTime dataHoraFinal2, Pageable pageable);

    Page<Reserva> findAllByAnuncioAnuncianteId(long idAnunciante, Pageable pageable);

    Optional<Reserva> findById(long idReserva);

    @Query("select (count(r) > 0) from Reserva r where r.anuncio.id = ?1 and r.pagamento.status <> ?2 and r.pagamento.status <> ?3 and (r.periodo.dataHoraInicial < ?4 or r.periodo.dataHoraInicial < ?5) and (r.periodo.dataHoraFinal > ?6 or r.periodo.dataHoraFinal > ?7)")
    boolean identificadorDePeriodo(Long id, StatusPagamento status, StatusPagamento status1, LocalDateTime dataHoraInicial, LocalDateTime dataHoraInicial1, LocalDateTime dataHoraFinal, LocalDateTime dataHoraFinal1);

}
