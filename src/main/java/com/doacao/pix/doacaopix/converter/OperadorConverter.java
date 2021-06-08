package com.doacao.pix.doacaopix.converter;

import com.doacao.pix.doacaopix.dto.OperadorDto;
import com.doacao.pix.doacaopix.model.Operador;

public class OperadorConverter {

    public OperadorConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static Operador toEntity(OperadorDto dto) {
        return Operador.builder()
                .codigo(dto.getCodigo())
                .cpfCnpj(dto.getCpfCnpj())
                .email(dto.getEmail())
                .endereco(dto.getCpfCnpj())
                .nome(dto.getNome())
                .senha(dto.getSenha())
                .tipo(dto.getTipo())
                .usuario(dto.getUsuario())
                .ultimoAcesso(dto.getUltimoAcesso())
                .build();
    }

    public static OperadorDto toDto(Operador entity) {
        return OperadorDto.builder()
                .codigo(entity.getCodigo())
                .cpfCnpj(entity.getCpfCnpj())
                .email(entity.getEmail())
                .endereco(entity.getCpfCnpj())
                .nome(entity.getNome())
                .senha(entity.getSenha())
                .tipo(entity.getTipo())
                .usuario(entity.getUsuario())
                .ultimoAcesso(entity.getUltimoAcesso())
                .build();
    }

}
