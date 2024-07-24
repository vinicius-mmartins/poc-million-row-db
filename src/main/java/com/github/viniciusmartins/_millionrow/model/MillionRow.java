package com.github.viniciusmartins._millionrow.model;

import lombok.Builder;

@Builder
public record MillionRow(
        Integer id,
        String colunaUm,
        String colunaDois,
        String colunaTres,
        Integer colunaQuatro
) {
}
