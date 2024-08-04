package com.codigocerto.desafiobackend.enums;

import lombok.Getter;

@Getter
public enum Status {
    ADIADA("ADIADA"),
    CONCLUIDA("CONCLUIDA"),
    EM_ANDAMENTO("EM_ANDAMENTO");

    final String value;

    Status(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
