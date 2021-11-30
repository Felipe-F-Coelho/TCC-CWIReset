package io.github.cwireset.tcc.mapper;

import io.github.cwireset.tcc.domain.Anuncio;
import io.github.cwireset.tcc.response.DadosAnuncioResponse;


import java.util.function.Function;



public class AnuncioMapper implements Function<Anuncio, DadosAnuncioResponse> {

    @Override
    public DadosAnuncioResponse apply(Anuncio anuncio) {
        return DadosAnuncioResponse.builder()
                .id(anuncio.getId())
                .imovel(anuncio.getImovel())
                .anunciante(anuncio.getAnunciante())
                .formasAceitas(anuncio.getFormasAceitas())
                .descricao(anuncio.getDescricao())
                .build();
    }
}
