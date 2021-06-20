package com.doacao.pix.doacaopix.controller;

import com.doacao.pix.doacaopix.dto.ResumoSorteioDoacoesDto;
import com.doacao.pix.doacaopix.dto.SorteioDto;
import com.doacao.pix.doacaopix.service.SorteioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "/sorteio")
public class SorteioController {

    private final SorteioService sorteioService;

    public SorteioController(SorteioService sorteioService) {
        this.sorteioService = sorteioService;
    }

    @PostMapping("/sortear")
    public ResponseEntity<List<SorteioDto>> sortear(@NotNull(message = "valorAproximadoPorDoacao é obrigatório") @RequestParam BigDecimal valorAproximadoPorDoacao,
                                                    @NotNull(message = "valorAproximadoPorDoacao é obrigatório") @RequestParam Integer qtdSorteados) {
        return ResponseEntity.ok()
                .body(this.sorteioService.sortearDoacao(valorAproximadoPorDoacao, qtdSorteados));
    }

    @GetMapping("/resumo")
    public ResponseEntity<ResumoSorteioDoacoesDto> buscarResumoDoacoes(){
        return ResponseEntity.ok()
                .body(this.sorteioService.buscarResumoDoacoes());
    }
}
