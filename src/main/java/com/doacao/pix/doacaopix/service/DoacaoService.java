package com.doacao.pix.doacaopix.service;

import com.doacao.pix.doacaopix.dto.DoacaoDto;
import com.doacao.pix.doacaopix.enums.StatusDoacao;
import com.doacao.pix.doacaopix.enums.TipoDoacao;
import com.doacao.pix.doacaopix.model.Doacao;
import com.doacao.pix.doacaopix.model.Doador;
import com.doacao.pix.doacaopix.repository.DoacaoRepository;
import com.doacao.pix.doacaopix.repository.DoadorRepository;
import com.doacao.pix.doacaopix.utils.NegocioException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

@Service
public class DoacaoService {

    private final DoadorRepository doadorRepository;
    private final DoacaoRepository doacaoRepository;

    public DoacaoService(DoadorRepository doadorRepository, DoacaoRepository doacaoRepository) {
        this.doadorRepository = doadorRepository;
        this.doacaoRepository = doacaoRepository;
    }

    public void novaDoacao(Long codigoDoador, DoacaoDto doacaoDto) throws NegocioException {
        if (doacaoDto.getTipo().equals(TipoDoacao.USUARIO)) {
            processarDoacaoPorUsuario(codigoDoador, doacaoDto);
            return;
        }
        processarDoacaoAnonima(doacaoDto);
    }

    private Doacao getDoacao(DoacaoDto doacaoDto) {
        return Doacao.builder()
                .data(LocalDateTime.now())
                .valor(doacaoDto.getValor())
                .codigoCobranca(doacaoDto.getCodigoCobranca())
                .statusDoacao(StatusDoacao.PENDENTE)
                .build();
    }

    private void processarDoacaoPorUsuario(Long codigoDoador, DoacaoDto doacaoDto) throws NegocioException {
        Doador doador = this.doadorRepository.findById(codigoDoador)
                .orElseThrow(() -> new NegocioException("Doador informado não existe!"));
        Doacao doacao = getDoacao(doacaoDto);
        doacao.setNomeDoador(doador.getNome());
        doador.getDoacaoList().add(doacao);
        this.doadorRepository.save(doador);
    }

    private void processarDoacaoAnonima(DoacaoDto doacaoDto){
        Doacao doacao = getDoacao(doacaoDto);
        doacao.setNomeDoador(TipoDoacao.ANONIMA.name());
        this.doacaoRepository.save(doacao);
    }

    public String aprovarDoacao(Long codigo, String status) {
        Optional<Doacao> doacao = this.doacaoRepository.findById(codigo);
        if (doacao.isPresent()) {
            doacao.get().setStatusDoacao(Arrays.stream(StatusDoacao.values())
                    .filter(s -> s.name().equals(status))
                    .findFirst().get());
            this.doacaoRepository.save(doacao.get());
            return "Doação aprovada com sucesso!";
        }
        return "Doação não existe";
    }

}
