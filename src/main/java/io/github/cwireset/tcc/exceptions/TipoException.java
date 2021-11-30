package io.github.cwireset.tcc.exceptions;

import lombok.Getter;

@Getter
public enum TipoException {

    ANUNCIO("Anuncio"),
    USUARIO("Usuario"),
    RESERVA("Reserva"),
    IMOVEL("Imovel"),
    NOME("Nome"),
    EMAIL("E-Mail"),
    CPF("CPF"),
    DATANASCIMENTO("data de nascimento"),
    ENDERECO("endereco"),
    CEP("CEP"),
    IDIMOVEL("IdImovel"),
    ID("Id");


    private final String singular;

    TipoException(String singular) {
        this.singular = singular;
    }

}
