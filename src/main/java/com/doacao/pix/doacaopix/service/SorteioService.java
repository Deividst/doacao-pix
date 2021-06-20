package com.doacao.pix.doacaopix.service;

import com.doacao.pix.doacaopix.converter.SorteioConverter;
import com.doacao.pix.doacaopix.dto.ResumoSorteioDoacoesDto;
import com.doacao.pix.doacaopix.dto.SorteioDto;
import com.doacao.pix.doacaopix.enums.StatusDoacao;
import com.doacao.pix.doacaopix.model.Doacao;
import com.doacao.pix.doacaopix.model.Donatario;
import com.doacao.pix.doacaopix.model.Sorteio;
import com.doacao.pix.doacaopix.repository.DoacaoRepository;
import com.doacao.pix.doacaopix.repository.DonatarioRepository;
import com.doacao.pix.doacaopix.repository.SorteioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SorteioService {

    private final DoacaoRepository doacaoRepository;
    private final DonatarioRepository donatarioRepository;
    private final SorteioRepository sorteioRepository;

    public SorteioService(DoacaoRepository doacaoRepository, DonatarioRepository donatarioRepository, SorteioRepository sorteioRepository) {
        this.doacaoRepository = doacaoRepository;
        this.donatarioRepository = donatarioRepository;
        this.sorteioRepository = sorteioRepository;
    }

    public ResumoSorteioDoacoesDto buscarResumoDoacoes(){
        List<Doacao> doacaoList = this.doacaoRepository.findByStatusDoacao(StatusDoacao.APROVADA);
        List<Donatario> donatarios = this.donatarioRepository.findAll();
        return ResumoSorteioDoacoesDto.builder()
                .quantidadeDonatarios(donatarios.size())
                .quantidadeDoacoes(doacaoList.size())
                .valorTotalDoacoes(doacaoList.stream().map(Doacao::getValor).reduce(BigDecimal.ZERO, BigDecimal::add))
                .build();
    }

    public List<SorteioDto> sortearDoacao(BigDecimal valorAproximadoPorDoacao, Integer qtdSorteados) {
        List<Doacao> doacaoList = this.doacaoRepository.findByStatusDoacao(StatusDoacao.APROVADA);

        List<Doacao> doacoesSelecionadas = new ArrayList<>();
        BigDecimal somaAtual = BigDecimal.ZERO;
        BigDecimal valorTotalDoacao = valorAproximadoPorDoacao.multiply(BigDecimal.valueOf(qtdSorteados));
        for (Doacao doacao : doacaoList) {
            if (somaAtual.add(doacao.getValor()).compareTo(valorTotalDoacao) <= 0) {
                somaAtual = somaAtual.add(doacao.getValor());
                doacoesSelecionadas.add(doacao);
            } else {
                break;
            }
        }

        BigDecimal valorPorDoacao = calcularValorPorDoacao(somaAtual, qtdSorteados);

        List<Donatario> donatariosSelecionados = sortearDonatarios(qtdSorteados);
        List<Sorteio> sorteados = definirSorteados(donatariosSelecionados, valorPorDoacao);
        atualizarDoacoesSorteadas(doacoesSelecionadas);
        enviarDoacao(doacoesSelecionadas);

        return SorteioConverter.toDtoList(sorteados);
    }

    private List<Donatario> sortearDonatarios(Integer quantidade) {
        List<Donatario> donatarios = this.donatarioRepository.findAll();
        Collections.shuffle(donatarios);

        if (donatarios.size() < quantidade) {
            return donatarios.subList(0, donatarios.size() - 1);
        }
        return donatarios.subList(0, quantidade);
    }

    private List<Sorteio> definirSorteados(List<Donatario> donatarios, BigDecimal valorPorDoacao) {
        List<Sorteio> sorteados = new ArrayList<>();
        for (Donatario donatario : donatarios) {
            Sorteio sorteio = Sorteio.builder()
                    .cpf(donatario.getCpfCnpj())
                    .nomeDonatario(donatario.getNome())
                    .valor(valorPorDoacao)
                    .dataHora(LocalDateTime.now())
                    .build();
            sorteados.add(sorteio);
        }
        return this.sorteioRepository.saveAll(sorteados);
    }

    private void atualizarDoacoesSorteadas(List<Doacao> doacoesSelecionadas) {
        doacoesSelecionadas.forEach(doacao -> doacao.setStatusDoacao(StatusDoacao.DOADA));
        this.doacaoRepository.saveAll(doacoesSelecionadas);
    }

    private BigDecimal calcularValorPorDoacao(BigDecimal somaAtual, Integer qtdSorteados) {
        return somaAtual.divide(BigDecimal.valueOf(qtdSorteados), 2, RoundingMode.HALF_UP);
    }

    private void enviarDoacao(List<Doacao> doacoesSelecionadas) {
        //Teria a implementação de um client rest chamando o serviço de algum banco
        //para realizar a transferencia do valor para o donatário
    }

}
