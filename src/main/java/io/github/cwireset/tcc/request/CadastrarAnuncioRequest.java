package io.github.cwireset.tcc.request;

import io.github.cwireset.tcc.domain.FormaPagamento;
import io.github.cwireset.tcc.domain.TipoAnuncio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class CadastrarAnuncioRequest {

    @NotNull(message = "Campo obrigatório não informado. Favor informar campo tipo do Anuncio")
    private TipoAnuncio tipoAnuncio;
    @NotNull(message = "Campo obrigatório não informado. Favor informar campo ID Imovel")
    private Long idImovel;
    @NotNull(message = "Campo obrigatório não informado. Favor informar campo ID Anunciante")
    private Long idAnunciante;
    @NotNull(message = "Campo obrigatório não informado. Favor informar campo valor da diaria")
    private BigDecimal valorDiaria;
    @NotNull(message = "Campo obrigatório não informado. Favor informar campo formas de pagamentos aceitas")
    @NotEmpty(message = "Campo obrigatório não informado. Favor informar campo formas de pagamentos aceitas")
    private List<FormaPagamento> formasAceitas;
    @NotBlank(message = "Campo obrigatório não informado. Favor informar campo descrição")
    private String descricao;

}
