package com.doacao.pix.doacaopix.service;

import com.doacao.pix.doacaopix.converter.DoadorConverter;
import com.doacao.pix.doacaopix.dto.DoadorDto;
import com.doacao.pix.doacaopix.model.Doacao;
import com.doacao.pix.doacaopix.model.Doador;
import com.doacao.pix.doacaopix.repository.DoacaoRepository;
import com.doacao.pix.doacaopix.repository.DoadorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class DoadorService {

    private final DoadorRepository doadorRepository;

    public DoadorService(DoadorRepository doadorRepository) {
        this.doadorRepository = doadorRepository;
    }

    public DoadorDto salvar(DoadorDto dto) {
        Doador doador = DoadorConverter.toEntity(dto);
        doador = this.doadorRepository.save(doador);
        return DoadorConverter.toDto(doador);
    }

    public DoadorDto atualizar(DoadorDto dto) {
        Optional<Doador> doador = this.doadorRepository.findById(dto.getCodigo());
        dto.setCodigo(doador.orElseThrow(EntityNotFoundException::new).getCodigo());
        this.doadorRepository.save(DoadorConverter.toEntity(dto));
        return dto;
    }

    public DoadorDto buscarPorCodigo(Long codigo) {
        return DoadorConverter.toDto(this.doadorRepository.findById(codigo)
                .orElseThrow(EntityNotFoundException::new));
    }

    public void excluir(Long codigo) {
        this.doadorRepository.deleteById(codigo);
    }
}
