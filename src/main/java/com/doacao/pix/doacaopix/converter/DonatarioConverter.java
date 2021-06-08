package com.doacao.pix.doacaopix.converter;

import com.doacao.pix.doacaopix.dto.DonatarioDto;
import com.doacao.pix.doacaopix.model.Donatario;

public class DonatarioConverter {

    public DonatarioConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static Donatario toEntity(DonatarioDto dto) {
        return Donatario.builder()
                .codigo(dto.getCodigo())
                .cpfCnpj(dto.getCpfCnpj())
                .email(dto.getEmail())
                .endereco(dto.getCpfCnpj())
                .nome(dto.getNome())
                .senha(dto.getSenha())
                .tipo(dto.getTipo())
                .usuario(dto.getUsuario())
                .chavePix(dto.getChavePix())
                .build();
    }

    public static DonatarioDto toDto(Donatario entity) {
        return DonatarioDto.builder()
                .codigo(entity.getCodigo())
                .cpfCnpj(entity.getCpfCnpj())
                .email(entity.getEmail())
                .endereco(entity.getCpfCnpj())
                .nome(entity.getNome())
                .senha(entity.getSenha())
                .tipo(entity.getTipo())
                .usuario(entity.getUsuario())
                .chavePix(entity.getChavePix())
                .build();
    }

}
