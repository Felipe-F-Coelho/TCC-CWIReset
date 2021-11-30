package io.github.cwireset.tcc.service;

import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.exceptions.*;
import io.github.cwireset.tcc.repository.UsuarioAvatarRepository;
import io.github.cwireset.tcc.repository.UsuarioRepository;
import io.github.cwireset.tcc.request.AtualizarUsuarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private UsuarioAvatarRepository usuarioAvatar;

    public Usuario cadastrarUsuario(Usuario usuario) throws Exception {

        if(repository.existsByEmail(usuario.getEmail()))
            throw new InstanciaJaCadastrada(TipoException.USUARIO.getSingular(), TipoException.EMAIL.getSingular(),usuario.getEmail());

        if(repository.existsByCpf(usuario.getCpf()))
            throw new InstanciaJaCadastrada(TipoException.USUARIO.getSingular(), TipoException.CPF.getSingular(),usuario.getCpf());

        usuario.setAvatar(usuarioAvatar.retornarAvatar());

        repository.save(usuario);

        return usuario;
    }

    public Page<Usuario> listarUsuarios(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Usuario buscarIdUsuario(Long idUsuario) throws Exception {
        return repository.findById(idUsuario)
                .orElseThrow(() -> new NenhumaInstanciaEncontradaException(TipoException.USUARIO.getSingular(), TipoException.ID.getSingular(), Long.toString(idUsuario)));
    }

    public Usuario buscarCpfUsuario(String cpf) throws Exception {
         return repository.findByCpf(cpf)
                .orElseThrow(() -> new NenhumaInstanciaEncontradaException(TipoException.USUARIO.getSingular(), TipoException.CPF.getSingular(), cpf));
    }

    public Usuario atualizarUsuario(Long id, AtualizarUsuarioRequest atualizarUsuarioRequest) throws Exception {

        Usuario buscarUsuario = buscarIdUsuario(id);

        if(repository.existsByEmailAndIdNot(atualizarUsuarioRequest.getEmail(), id))
            throw new InstanciaJaCadastrada(TipoException.USUARIO.getSingular(), TipoException.EMAIL.getSingular(),atualizarUsuarioRequest.getEmail());

        buscarUsuario.setNome(atualizarUsuarioRequest.getNome());
        buscarUsuario.setEmail(atualizarUsuarioRequest.getEmail());
        buscarUsuario.setSenha(atualizarUsuarioRequest.getSenha());
        buscarUsuario.setDataNascimento(atualizarUsuarioRequest.getDataNascimento());

        buscarUsuario.getEndereco().setBairro(atualizarUsuarioRequest.getEndereco().getBairro());
        buscarUsuario.getEndereco().setCep(atualizarUsuarioRequest.getEndereco().getCep());
        buscarUsuario.getEndereco().setCidade(atualizarUsuarioRequest.getEndereco().getCidade());
        buscarUsuario.getEndereco().setComplemento(atualizarUsuarioRequest.getEndereco().getComplemento());
        buscarUsuario.getEndereco().setEstado(atualizarUsuarioRequest.getEndereco().getEstado());
        buscarUsuario.getEndereco().setLogradouro(atualizarUsuarioRequest.getEndereco().getLogradouro());
        buscarUsuario.getEndereco().setNumero(atualizarUsuarioRequest.getEndereco().getNumero());

        return repository.save(buscarUsuario);
    }

}
