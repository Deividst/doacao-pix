package com.doacao.pix.doacaopix.converter;

import com.doacao.pix.doacaopix.dto.DoadorDto;
import com.doacao.pix.doacaopix.model.Doador;
import com.doacao.pix.doacaopix.model.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Base64;
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
                .usuario(Usuario.builder()
                        .codigo(dto.getCodigoUsuario())
                        .nome(dto.getUsuario())
                        .senha(Base64.getEncoder().encodeToString(dto.getSenha().getBytes()))
                        .tipo(dto.getTipo())
                        .build())
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
                .tipo(entity.getUsuario().getTipo())
                .usuario(entity.getUsuario().getNome())
                .senha(new String(Base64.getDecoder().decode(entity.getUsuario().getSenha())))
                .codigoUsuario(entity.getUsuario().getCodigo())
                .doacaoList(Objects.isNull(entity.getDoacaoList()) ? null : DoacaoConverter.toDtoList(entity.getDoacaoList()))
                .build();
    }

}
