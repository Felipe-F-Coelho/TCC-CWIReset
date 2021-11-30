package io.github.cwireset.tcc.controller;

import io.github.cwireset.tcc.domain.Imovel;
import io.github.cwireset.tcc.request.CadastrarImovelRequest;
import io.github.cwireset.tcc.service.ImovelService;
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
@RequestMapping("/imoveis")
public class ImovelController {

    @Autowired
    private ImovelService imovelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Imovel cadastrarImovel(@RequestBody @Valid CadastrarImovelRequest cadastrarImovelRequest) throws Exception{
        return imovelService.cadastrarImovel(cadastrarImovelRequest);
    }

    @GetMapping
    public Page<Imovel> listarImoveis(@PageableDefault(sort = "identificacao", direction = Sort.Direction.ASC) @ApiIgnore Pageable pageable){
        return imovelService.listarImoveis(pageable);
    }

    @GetMapping("/proprietarios/{idProprietario}")
    public Page<Imovel> listarImoveisProprietario(@PathVariable @Valid long idProprietario, @PageableDefault(sort = "identificacao", direction = Sort.Direction.ASC) @ApiIgnore Pageable pageable) throws Exception {
        return imovelService.listarImoveisProprietario(idProprietario,pageable);
    }

    @GetMapping("/{idImovel}")
    public Imovel listaImovelId(@PathVariable @Valid long idImovel) throws Exception {
        return imovelService.buscarImovelId(idImovel);
    }

    @DeleteMapping("/{idImovel}")
    public void deletarImovelId(@PathVariable @Valid long idImovel) throws Exception{
        imovelService.deletarImovelId(idImovel);
    }
}
