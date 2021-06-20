package com.doacao.pix.doacaopix.controller;

import com.doacao.pix.doacaopix.dto.DoadorDto;
import com.doacao.pix.doacaopix.service.DoadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(path = "/doador")
public class DoadorController {

    private final DoadorService doadorService;

    public DoadorController(DoadorService doadorService) {
        this.doadorService = doadorService;
    }

    @PostMapping
    public ResponseEntity<DoadorDto> save(@Valid @RequestBody DoadorDto doadorDto) {
        return ResponseEntity.ok()
                .body(this.doadorService.salvar(doadorDto));
    }

    @PutMapping
    public ResponseEntity<DoadorDto> update(@Valid @RequestBody DoadorDto doadorDto) {
        return ResponseEntity.ok()
                .body(this.doadorService.atualizar(doadorDto));
    }

    @DeleteMapping(path = "/{codigo}")
    public ResponseEntity<?> delete(@NotNull(message = "Codigo no path é obrigatório") @PathVariable Long codigo) {
        this.doadorService.excluir(codigo);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{codigo}")
    public ResponseEntity<DoadorDto> findById(@NotNull(message = "Codigo no path é obrigatório") @PathVariable Long codigo) {
        DoadorDto doadorDto = this.doadorService.buscarPorCodigo(codigo);
        return doadorDto != null ? ResponseEntity.ok(doadorDto) : ResponseEntity.noContent().build();
    }
}
