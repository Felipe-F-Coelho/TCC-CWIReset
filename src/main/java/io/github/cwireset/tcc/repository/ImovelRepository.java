package io.github.cwireset.tcc.repository;

import io.github.cwireset.tcc.domain.Imovel;
import io.github.cwireset.tcc.domain.TipoStatus;
import io.github.cwireset.tcc.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel,Long> {

    Page<Imovel> findAllByProprietarioAndTipoStatus(Usuario proprietario, TipoStatus available, Pageable pageable);

    Page<Imovel> findAllByTipoStatus(TipoStatus available, Pageable pageable);

    Optional<Imovel> findByIdAndTipoStatus(long idImovel, TipoStatus available);
}
