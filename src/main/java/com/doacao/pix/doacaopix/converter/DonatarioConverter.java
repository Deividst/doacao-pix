package com.doacao.pix.doacaopix.converter;

import com.doacao.pix.doacaopix.dto.DonatarioDto;
import com.doacao.pix.doacaopix.model.Donatario;
import com.doacao.pix.doacaopix.model.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Base64;

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
                .usuario(Usuario.builder()
                        .codigo(dto.getCodigoUsuario())
                        .nome(dto.getUsuario())
                        .senha(Base64.getEncoder().encodeToString(dto.getSenha().getBytes()))
                        .tipo(dto.getTipo())
                        .build())
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
                .tipo(entity.getUsuario().getTipo())
                .usuario(entity.getUsuario().getNome())
                .senha(new String(Base64.getDecoder().decode(entity.getUsuario().getSenha())))
                .codigoUsuario(entity.getUsuario().getCodigo())
                .chavePix(entity.getChavePix())
                .build();
    }

}
