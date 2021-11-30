package io.github.cwireset.tcc.controller;

import io.github.cwireset.tcc.domain.Anuncio;
import io.github.cwireset.tcc.request.CadastrarAnuncioRequest;
import io.github.cwireset.tcc.service.AnuncioService;
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
@RequestMapping("/anuncios")
public class AnuncioController {

    @Autowired
    AnuncioService anuncioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Anuncio cadastrarAnuncio(@RequestBody @Valid CadastrarAnuncioRequest cadastrarAnuncioRequest) throws Exception {
        return anuncioService.cadastrarAnuncio(cadastrarAnuncioRequest);
    }

    @GetMapping
    public Page<Anuncio> listarAnuncio(@PageableDefault(sort = "valorDiaria", direction = Sort.Direction.ASC) @ApiIgnore Pageable pageable){
        return anuncioService.listarAnuncio(pageable);
    }

    @GetMapping("/anunciantes/{idAnunciante}")
    public Page<Anuncio> listarAnuncioIdAnunciante(@PathVariable @Valid long idAnunciante, @PageableDefault(sort = "valorDiaria",
            direction = Sort.Direction.ASC) @ApiIgnore Pageable pageable) throws Exception {
        return anuncioService.listarAnuncioIdAnunciante(idAnunciante,pageable);
    }

    @DeleteMapping("/{idAnuncio}")
    public void deletarAnuncioId(@PathVariable @Valid long idAnuncio) throws Exception {
        anuncioService.deletarAnuncioId(idAnuncio);
    }
}
