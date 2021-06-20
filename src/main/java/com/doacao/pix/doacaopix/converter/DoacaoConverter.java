package com.doacao.pix.doacaopix.converter;

import com.doacao.pix.doacaopix.dto.DoacaoDto;
import com.doacao.pix.doacaopix.model.Doacao;

import java.util.List;
import java.util.stream.Collectors;

public class DoacaoConverter {

    public DoacaoConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static Doacao toEntity(DoacaoDto dto) {
        return Doacao.builder()
                .codigo(dto.getCodigo())
                .statusDoacao(dto.getStatusDoacao())
                .valor(dto.getValor())
                .nomeDoador(dto.getNomeDoador())
                .data(dto.getData())
                .codigoCobranca(dto.getCodigoCobranca())
                .build();
    }

    public static DoacaoDto toDto(Doacao entity) {
        return DoacaoDto.builder()
                .codigo(entity.getCodigo())
                .statusDoacao(entity.getStatusDoacao())
                .valor(entity.getValor())
                .nomeDoador(entity.getNomeDoador())
                .data(entity.getData())
                .codigoCobranca(entity.getCodigoCobranca())
                .build();
    }

    public static List<DoacaoDto> toDtoList(List<Doacao> entityList) {
        return entityList.stream()
                .map(DoacaoConverter::toDto)
                .collect(Collectors.toList());
    }

    public static List<Doacao> toEntityList(List<DoacaoDto> dtoList) {
        return dtoList.stream()
                .map(DoacaoConverter::toEntity)
                .collect(Collectors.toList());
    }

}
