package io.github.cwireset.tcc.request;

import io.github.cwireset.tcc.domain.Endereco;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AtualizarUsuarioRequest {

    @NotBlank(message = "Campo obrigatório não informado. Favor informar campo nome")
    private String nome;
    @NotBlank(message = "Campo obrigatório não informado. Favor informar campo email")
    private String email;
    @NotBlank(message = "Campo obrigatório não informado. Favor informar campo senha")
    private String senha;
    @NotNull(message = "Campo obrigatório não informado. Favor informar campo data de nascimento")
    private LocalDate dataNascimento;
    @Valid
    private Endereco endereco;

}
