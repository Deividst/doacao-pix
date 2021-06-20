package com.doacao.pix.doacaopix.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "TB_SORTEIO")
public class Sorteio implements Serializable {

    private static final long serialVersionUID = 5861835075684347350L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    @Column(name = "NOME_DONATARIO")
    private String nomeDonatario;

    private String cpf;

    private BigDecimal valor;

    private LocalDateTime dataHora;

}
