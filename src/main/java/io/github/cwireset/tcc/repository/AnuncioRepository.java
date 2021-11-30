package io.github.cwireset.tcc.repository;

import io.github.cwireset.tcc.domain.Anuncio;
import io.github.cwireset.tcc.domain.Imovel;
import io.github.cwireset.tcc.domain.TipoStatus;
import io.github.cwireset.tcc.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

    boolean existsByImovel(Imovel imovel);

    Page<Anuncio> findByTipoStatus(TipoStatus available, Pageable pageable);

    Page<Anuncio> findAllByAnuncianteAndTipoStatus(Usuario anunciante,TipoStatus available, Pageable pageable);

    Optional<Object> findByIdAndTipoStatus(long idAnuncio, TipoStatus tipoStatus);

    boolean existsByImovelAndTipoStatus(Imovel imovel, TipoStatus available);
}
