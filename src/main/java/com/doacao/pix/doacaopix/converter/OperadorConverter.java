package com.doacao.pix.doacaopix.converter;

import com.doacao.pix.doacaopix.dto.OperadorDto;
import com.doacao.pix.doacaopix.model.Operador;
import com.doacao.pix.doacaopix.model.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Base64;

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
                .usuario(Usuario.builder()
                        .codigo(dto.getCodigoUsuario())
                        .nome(dto.getUsuario())
                        .senha(Base64.getEncoder().encodeToString(dto.getSenha().getBytes()))
                        .tipo(dto.getTipo())
                        .build())
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
                .tipo(entity.getUsuario().getTipo())
                .usuario(entity.getUsuario().getNome())
                .senha(new String(Base64.getDecoder().decode(entity.getUsuario().getSenha())))
                .codigoUsuario(entity.getUsuario().getCodigo())
                .ultimoAcesso(entity.getUltimoAcesso())
                .build();
    }

}
