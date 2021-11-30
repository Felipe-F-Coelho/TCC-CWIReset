package io.github.cwireset.tcc.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Campo obrigatório não informado. Favor informar campo CEP")
    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "O CEP deve ser informado no formato 99999-999.")
    private String cep;

    @NotBlank(message = "Campo obrigatório não informado. Favor informar campo logradouro")
    private String logradouro;

    @NotBlank(message = "Campo obrigatório não informado. Favor informar campo numero")
    private String numero;

    private String complemento;

    @NotBlank(message = "Campo obrigatório não informado. Favor informar campo bairro")
    private String bairro;

    @NotBlank(message = "Campo obrigatório não informado. Favor informar campo cidade")
    private String cidade;

    @NotBlank(message = "Campo obrigatório não informado. Favor informar campo estado")
    private String estado;

}
