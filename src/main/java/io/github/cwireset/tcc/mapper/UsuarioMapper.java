package io.github.cwireset.tcc.mapper;

import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.response.DadosSolicitanteResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UsuarioMapper implements Function<Usuario, DadosSolicitanteResponse> {

    @Override
    public DadosSolicitanteResponse apply(Usuario usuario) {
        return DadosSolicitanteResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .build();
    }

//    public List<UsuarioResponse> apply(Page<Usuario> usuarios) {
//
//        return usuarios
//                .stream()
//                .map(usuario -> new UsuarioMapper().apply(usuario))
//                .collect(Collectors.toList());
//    }
}
