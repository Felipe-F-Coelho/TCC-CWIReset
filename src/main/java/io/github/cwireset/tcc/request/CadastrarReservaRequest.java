package io.github.cwireset.tcc.request;

import io.github.cwireset.tcc.domain.Periodo;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CadastrarReservaRequest {

    @NotNull(message = "Campo obrigatório não informado. Favor informar campo id do solicitante")
    private Long idSolicitante;
    @NotNull(message = "Campo obrigatório não informado. Favor informar campo id do anuncio")
    private Long idAnuncio;
    @NotNull(message = "Campo obrigatório não informado. Favor informar campo periodo")
    @Valid
    private Periodo periodo;
    @NotNull(message = "Campo obrigatório não informado. Favor informar campo quantidade de pessoas")
    private Integer quantidadePessoas;

}
