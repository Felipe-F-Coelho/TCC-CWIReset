package io.github.cwireset.tcc.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Builder
public class DadosSolicitanteResponse {

    private long id;
    private String nome;

}
