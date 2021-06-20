package com.doacao.pix.doacaopix.dto;

import com.doacao.pix.doacaopix.enums.StatusDoacao;
import com.doacao.pix.doacaopix.enums.TipoDoacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DoacaoDto {

    private Long codigo;

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime data;

    private String nomeDoador;

    @NotNull(message = "O campo valor é obrigatório")
    private BigDecimal valor;

    private StatusDoacao statusDoacao;

    @NotNull(message = "O campo tipo é obrigatório")
    private TipoDoacao tipo;

    @NotNull(message = "O campo codigoCobranca é obrigatório")
    private String codigoCobranca;

}
