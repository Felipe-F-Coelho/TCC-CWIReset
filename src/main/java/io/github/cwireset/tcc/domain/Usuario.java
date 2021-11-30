package io.github.cwireset.tcc.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Campo obrigatório não informado. Favor informar campo nome")
    private String nome;

    @NotBlank(message = "Campo obrigatório não informado. Favor informar campo email")
    private String email;

    @NotBlank(message = "Campo obrigatório não informado. Favor informar campo senha")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @NotBlank(message = "Campo obrigatório não informado. Favor informar campo CPF")
    @Column(unique = true)
    @Pattern(regexp = "-?\\d{11}+", message = "O CPF deve ser informado no formato 99999999999.")
    private String cpf;

    @NotNull(message = "Campo obrigatório não informado. Favor informar campo data de nascimento")
    private LocalDate dataNascimento;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    @Valid
    private Endereco endereco;

    @Embedded
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Avatar avatar;
}
