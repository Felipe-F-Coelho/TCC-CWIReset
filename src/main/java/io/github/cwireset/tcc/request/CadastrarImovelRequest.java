package io.github.cwireset.tcc.request;

import io.github.cwireset.tcc.domain.CaracteristicaImovel;
import io.github.cwireset.tcc.domain.Endereco;
import io.github.cwireset.tcc.domain.TipoImovel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CadastrarImovelRequest {

    @NotBlank(message = "Campo obrigatório não informado. Favor informar campo identificação")
    private String identificacao;
    @NotNull(message = "Campo obrigatório não informado. Favor informar campo tipo do imovel")
    private TipoImovel tipoImovel;
    @Valid
    @NotNull(message = "Campo obrigatório não informado. Favor informar campo endereço")
    private Endereco endereco;
    @NotNull(message = "Campo obrigatório não informado. Favor informar campo id Proprietario")
    private Long idProprietario;
    private List<CaracteristicaImovel> caracteristicas;

}
