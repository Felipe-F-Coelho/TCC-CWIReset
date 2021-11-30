package io.github.cwireset.tcc.service;

import io.github.cwireset.tcc.domain.Imovel;
import io.github.cwireset.tcc.domain.TipoStatus;
import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.exceptions.ImpossibilityExclusion;
import io.github.cwireset.tcc.exceptions.NenhumaInstanciaEncontradaException;
import io.github.cwireset.tcc.exceptions.TipoException;
import io.github.cwireset.tcc.repository.ImovelRepository;
import io.github.cwireset.tcc.request.CadastrarImovelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ImovelService {

    @Autowired
    ImovelRepository repository;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    AnuncioService anuncioService;

    public Imovel cadastrarImovel(CadastrarImovelRequest cadastrarImovelRequest) throws Exception {

        Usuario buscarIdUsuario = usuarioService.buscarIdUsuario(cadastrarImovelRequest.getIdProprietario());

        Imovel newImovel = Imovel.builder()
                .endereco(cadastrarImovelRequest.getEndereco())
                .tipoImovel(cadastrarImovelRequest.getTipoImovel())
                .caracteristicas(cadastrarImovelRequest.getCaracteristicas())
                .identificacao(cadastrarImovelRequest.getIdentificacao())
                .proprietario(buscarIdUsuario)
                .tipoStatus(TipoStatus.AVAILABLE)
                .build();

        repository.save(newImovel);

        return newImovel;
    }

    public Page<Imovel> listarImoveis(Pageable pageable) {
        return repository.findAllByTipoStatus(TipoStatus.AVAILABLE,pageable);
    }

    public Page<Imovel> listarImoveisProprietario(long idProprietario, Pageable pageable) throws Exception {

        Usuario proprietario = usuarioService.buscarIdUsuario(idProprietario);

        return repository.findAllByProprietarioAndTipoStatus(proprietario,TipoStatus.AVAILABLE,pageable);
    }

    public Imovel buscarImovelId(long idImovel) throws Exception{
        return repository.findByIdAndTipoStatus(idImovel,TipoStatus.AVAILABLE)
                .orElseThrow(() -> new NenhumaInstanciaEncontradaException(TipoException.IMOVEL.getSingular(), TipoException.ID.getSingular(), Long.toString(idImovel)));
    }

    public void deletarImovelId(long idImovel) throws Exception {
        Imovel imovel = buscarImovelId(idImovel);

        if(anuncioService.verificarImovelAnuncio(imovel))
            throw new ImpossibilityExclusion();

        imovel.setTipoStatus(TipoStatus.NOT_AVAILABLE);

        repository.save(imovel);
    }
}
