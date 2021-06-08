package com.doacao.pix.doacaopix.converter;

import com.doacao.pix.doacaopix.dto.DoadorDto;
import com.doacao.pix.doacaopix.model.Doador;

import java.util.Objects;

public class DoadorConverter {

    public DoadorConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static Doador toEntity(DoadorDto dto) {
        return Doador.builder()
                .codigo(dto.getCodigo())
                .cpfCnpj(dto.getCpfCnpj())
                .email(dto.getEmail())
                .endereco(dto.getCpfCnpj())
                .nome(dto.getNome())
                .senha(dto.getSenha())
                .tipo(dto.getTipo())
                .usuario(dto.getUsuario())
                .doacaoList(Objects.isNull(dto.getDoacaoList()) ? null : DoacaoConverter.toEntityList(dto.getDoacaoList()))
                .build();
    }

    public static DoadorDto toDto(Doador entity) {
        return DoadorDto.builder()
                .codigo(entity.getCodigo())
                .cpfCnpj(entity.getCpfCnpj())
                .email(entity.getEmail())
                .endereco(entity.getCpfCnpj())
                .nome(entity.getNome())
                .senha(entity.getSenha())
                .tipo(entity.getTipo())
                .usuario(entity.getUsuario())
                .doacaoList(Objects.isNull(entity.getDoacaoList()) ? null : DoacaoConverter.toDtoList(entity.getDoacaoList()))
                .build();
    }

}
