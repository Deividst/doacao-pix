package com.doacao.pix.doacaopix.controller;

import com.doacao.pix.doacaopix.dto.OperadorDto;
import com.doacao.pix.doacaopix.service.OperadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(path = "/operador")
public class OperadorController {

    private final OperadorService operadorService;

    public OperadorController(OperadorService operadorService) {
        this.operadorService = operadorService;
    }

    @PostMapping
    public ResponseEntity<OperadorDto> save(@Valid @RequestBody OperadorDto operadorDto) {
        return ResponseEntity.ok()
                .body(this.operadorService.salvar(operadorDto));
    }

    @PutMapping
    public ResponseEntity<OperadorDto> update(@Valid @RequestBody OperadorDto operadorDto) {
        return ResponseEntity.ok()
                .body(this.operadorService.atualizar(operadorDto));
    }

    @DeleteMapping(path = "/{codigo}")
    public ResponseEntity<?> delete(@NotNull(message = "Codigo no path é obrigatório") @PathVariable Long codigo) {
        this.operadorService.excluir(codigo);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{codigo}")
    public ResponseEntity<OperadorDto> findById(@NotNull(message = "Codigo no path é obrigatório") @PathVariable Long codigo) {
        OperadorDto operadorDto = this.operadorService.buscarPorCodigo(codigo);
        return operadorDto != null ? ResponseEntity.ok(operadorDto) : ResponseEntity.noContent().build();
    }
}
