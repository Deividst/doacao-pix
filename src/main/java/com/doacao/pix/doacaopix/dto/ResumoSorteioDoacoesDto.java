package com.doacao.pix.doacaopix.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumoSorteioDoacoesDto implements Serializable {

    private BigDecimal valorTotalDoacoes;
    private Integer quantidadeDonatarios;
    private Integer quantidadeDoacoes;

}
