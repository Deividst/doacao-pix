package com.doacao.pix.doacaopix.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SorteioDto implements Serializable {

    private static final long serialVersionUID = 4194667713213116141L;

    private Long codigo;

    private String nomeDonatario;

    private String cpf;

    private BigDecimal valor;

    private LocalDateTime dataHora;

}
