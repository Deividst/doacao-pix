package com.doacao.pix.doacaopix.service;

import com.doacao.pix.doacaopix.converter.DoacaoConverter;
import com.doacao.pix.doacaopix.converter.DoadorConverter;
import com.doacao.pix.doacaopix.dto.DoadorDto;
import com.doacao.pix.doacaopix.model.Doador;
import com.doacao.pix.doacaopix.repository.DoadorRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
        Doador doador = this.doadorRepository.findById(dto.getCodigo()).orElseThrow(EntityNotFoundException::new);
        dto.setCodigo(doador.getCodigo());
        dto.getDoacaoList().addAll(DoacaoConverter.toDtoList(doador.getDoacaoList()));
        doador = this.doadorRepository.save(DoadorConverter.toEntity(dto));
        return DoadorConverter.toDto(doador);
    }

    public DoadorDto buscarPorCodigo(Long codigo) {
        Optional<Doador> doador = this.doadorRepository.findById(codigo);
        return doador.map(DoadorConverter::toDto).orElse(null);
    }

    public void excluir(Long codigo) {
        this.doadorRepository.deleteById(codigo);
    }
}
