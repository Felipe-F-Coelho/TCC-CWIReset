package io.github.cwireset.tcc.controller;

import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.request.AtualizarUsuarioRequest;
import io.github.cwireset.tcc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario cadastrarUsuario(@RequestBody @Valid Usuario usuario) throws Exception {
        return usuarioService.cadastrarUsuario(usuario);
    }

    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable @Valid Long id, @RequestBody @Valid AtualizarUsuarioRequest atualizarUsuarioRequest) throws Exception {
        return usuarioService.atualizarUsuario(id, atualizarUsuarioRequest);
    }

    @GetMapping
    public Page<Usuario> listarUsuarios(@PageableDefault(sort = "nome", direction = Sort.Direction.ASC) @ApiIgnore Pageable pageable){
        return usuarioService.listarUsuarios(pageable);
    }

    @GetMapping("/{idUsuario}")
    public Usuario buscarIdUsuario(@PathVariable @Valid Long idUsuario) throws Exception{
        return usuarioService.buscarIdUsuario(idUsuario);
    }

    @GetMapping("/cpf/{cpf}")
    public Usuario buscarCpfUsuario(@PathVariable @Valid String cpf) throws Exception {
        return usuarioService.buscarCpfUsuario(cpf);
    }

}
