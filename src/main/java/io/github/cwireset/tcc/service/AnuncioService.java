package io.github.cwireset.tcc.service;

import io.github.cwireset.tcc.domain.Anuncio;
import io.github.cwireset.tcc.domain.Imovel;
import io.github.cwireset.tcc.domain.TipoStatus;
import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.exceptions.InstanciaJaCadastrada;
import io.github.cwireset.tcc.exceptions.NenhumaInstanciaEncontradaException;
import io.github.cwireset.tcc.exceptions.TipoException;
import io.github.cwireset.tcc.repository.AnuncioRepository;
import io.github.cwireset.tcc.request.CadastrarAnuncioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnuncioService {

    @Autowired
    AnuncioRepository repository;
    @Autowired
    ImovelService imovelService;
    @Autowired
    UsuarioService usuarioService;

    public Anuncio cadastrarAnuncio(CadastrarAnuncioRequest cadastrarAnuncioRequest) throws Exception {

        Imovel imovel = imovelService.buscarImovelId(cadastrarAnuncioRequest.getIdImovel());

        Usuario usuario = usuarioService.buscarIdUsuario(cadastrarAnuncioRequest.getIdAnunciante());

        if(verificarImovelAnuncio(imovel))
            throw new InstanciaJaCadastrada(TipoException.ANUNCIO.getSingular(),TipoException.IDIMOVEL.getSingular(),Long.toString(cadastrarAnuncioRequest.getIdImovel()));

        Anuncio newAnuncio = Anuncio.builder()
                .tipoAnuncio(cadastrarAnuncioRequest.getTipoAnuncio())
                .imovel(imovel)
                .anunciante(usuario)
                .valorDiaria(cadastrarAnuncioRequest.getValorDiaria())
                .formasAceitas(cadastrarAnuncioRequest.getFormasAceitas())
                .descricao(cadastrarAnuncioRequest.getDescricao())
                .tipoStatus(TipoStatus.AVAILABLE)
                .build();

        repository.save(newAnuncio);

        return newAnuncio;
    }

    public Page<Anuncio> listarAnuncio(Pageable pageable) {
        return repository.findByTipoStatus(TipoStatus.AVAILABLE,pageable);
    }

    public Page<Anuncio> listarAnuncioIdAnunciante(long id, Pageable pageable) throws Exception {

        Usuario anunciante = usuarioService.buscarIdUsuario(id);

        return repository.findAllByAnuncianteAndTipoStatus(anunciante,TipoStatus.AVAILABLE, pageable);
    }

    public boolean verificarImovelAnuncio(Imovel imovel){
        return repository.existsByImovelAndTipoStatus(imovel,TipoStatus.AVAILABLE);
    }

    public Anuncio buscarIdAnuncio(long idAnuncio) throws Exception {
        return (Anuncio) repository.findByIdAndTipoStatus(idAnuncio, TipoStatus.AVAILABLE)
                .orElseThrow(() -> new NenhumaInstanciaEncontradaException(TipoException.ANUNCIO.getSingular(), TipoException.ID.getSingular(), Long.toString(idAnuncio)));
    }

    public void deletarAnuncioId(long idAnuncio) throws Exception {

        Anuncio anuncio = buscarIdAnuncio(idAnuncio);

        anuncio.setTipoStatus(TipoStatus.NOT_AVAILABLE);

        repository.save(anuncio);
    }
}
