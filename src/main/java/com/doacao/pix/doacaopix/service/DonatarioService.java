package com.doacao.pix.doacaopix.service;

import com.doacao.pix.doacaopix.converter.DonatarioConverter;
import com.doacao.pix.doacaopix.dto.DonatarioDto;
import com.doacao.pix.doacaopix.model.Donatario;
import com.doacao.pix.doacaopix.repository.DonatarioRepository;
import com.doacao.pix.doacaopix.utils.NegocioException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class DonatarioService {

    private final DonatarioRepository donatarioRepository;

    public DonatarioService(DonatarioRepository donatarioRepository) {
        this.donatarioRepository = donatarioRepository;
    }

    public DonatarioDto salvar(DonatarioDto dto) {
        Donatario donatario = DonatarioConverter.toEntity(dto);
        donatario = this.donatarioRepository.save(donatario);
        return DonatarioConverter.toDto(donatario);
    }

    public DonatarioDto atualizar(DonatarioDto dto) {
        Optional<Donatario> donatario = this.donatarioRepository.findById(dto.getCodigo());
        dto.setCodigo(donatario.orElseThrow(EntityNotFoundException::new).getCodigo());
        Donatario donatarioSalvo = this.donatarioRepository.save(DonatarioConverter.toEntity(dto));
        return DonatarioConverter.toDto(donatarioSalvo);
    }

    public DonatarioDto buscarPorCodigo(Long codigo) {
        Optional<Donatario> donatario = this.donatarioRepository.findById(codigo);
        return donatario.map(DonatarioConverter::toDto).orElse(null);
    }

    public void excluir(Long codigo) {
        this.donatarioRepository.deleteById(codigo);
    }
}
