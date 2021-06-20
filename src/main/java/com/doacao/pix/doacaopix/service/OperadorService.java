package com.doacao.pix.doacaopix.service;

import com.doacao.pix.doacaopix.converter.DoadorConverter;
import com.doacao.pix.doacaopix.converter.OperadorConverter;
import com.doacao.pix.doacaopix.dto.DoadorDto;
import com.doacao.pix.doacaopix.dto.OperadorDto;
import com.doacao.pix.doacaopix.model.Doador;
import com.doacao.pix.doacaopix.model.Operador;
import com.doacao.pix.doacaopix.repository.OperadorRepository;
import com.doacao.pix.doacaopix.utils.NegocioException;
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
        Operador operadorSalvo = this.operadorRepository.save(OperadorConverter.toEntity(dto));
        return OperadorConverter.toDto(operadorSalvo);
    }

    public OperadorDto buscarPorCodigo(Long codigo) {
        Optional<Operador> operador = this.operadorRepository.findById(codigo);
        return operador.map(OperadorConverter::toDto).orElse(null);
    }

    public void excluir(Long codigo) {
        this.operadorRepository.deleteById(codigo);
    }
}
