package com.doacao.pix.doacaopix.converter;

import com.doacao.pix.doacaopix.dto.SorteioDto;
import com.doacao.pix.doacaopix.model.Sorteio;

import java.util.List;
import java.util.stream.Collectors;

public class SorteioConverter {

    public SorteioConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static Sorteio toEntity(SorteioDto dto) {
        return Sorteio.builder()
                .nomeDonatario(dto.getNomeDonatario())
                .valor(dto.getValor())
                .dataHora(dto.getDataHora())
                .cpf(dto.getCpf())
                .build();
    }

    public static SorteioDto toDto(Sorteio entity) {
        return SorteioDto.builder()
                .nomeDonatario(entity.getNomeDonatario())
                .valor(entity.getValor())
                .dataHora(entity.getDataHora())
                .cpf(entity.getCpf())
                .codigo(entity.getCodigo())
                .build();
    }

    public static List<SorteioDto> toDtoList(List<Sorteio> entityList) {
        return entityList.stream()
                .map(SorteioConverter::toDto)
                .collect(Collectors.toList());
    }
}
