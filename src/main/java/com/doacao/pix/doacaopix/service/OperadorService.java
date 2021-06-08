package com.doacao.pix.doacaopix.service;

import com.doacao.pix.doacaopix.converter.DoadorConverter;
import com.doacao.pix.doacaopix.converter.OperadorConverter;
import com.doacao.pix.doacaopix.dto.DoadorDto;
import com.doacao.pix.doacaopix.dto.OperadorDto;
import com.doacao.pix.doacaopix.model.Doador;
import com.doacao.pix.doacaopix.model.Operador;
import com.doacao.pix.doacaopix.repository.OperadorRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class OperadorService {

    private final OperadorRepository operadorRepository;

    public OperadorService(OperadorRepository operadorRepository) {
        this.operadorRepository = operadorRepository;
    }

    public OperadorDto salvar(OperadorDto dto) {
        Operador operador = OperadorConverter.toEntity(dto);
        operador = this.operadorRepository.save(operador);
        return OperadorConverter.toDto(operador);
    }

    public OperadorDto atualizar(OperadorDto dto) {
        Optional<Operador> operador = this.operadorRepository.findById(dto.getCodigo());
        dto.setCodigo(operador.orElseThrow(EntityNotFoundException::new).getCodigo());
        this.operadorRepository.save(OperadorConverter.toEntity(dto));
        return dto;
    }

    public OperadorDto buscarPorCodigo(Long codigo) {
        return OperadorConverter.toDto(this.operadorRepository.findById(codigo)
                .orElseThrow(EntityNotFoundException::new));
    }

    public void excluir(Long codigo) {
        this.operadorRepository.deleteById(codigo);
    }
}
