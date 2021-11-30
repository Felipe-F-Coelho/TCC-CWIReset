package io.github.cwireset.tcc.response;

import io.github.cwireset.tcc.domain.FormaPagamento;
import io.github.cwireset.tcc.domain.Imovel;
import io.github.cwireset.tcc.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class DadosAnuncioResponse {

    private long id;
    private Imovel imovel;
    private Usuario anunciante;
    private List<FormaPagamento> formasAceitas;
    private String descricao;

}
