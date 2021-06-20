package com.doacao.pix.doacaopix.controller;

import com.doacao.pix.doacaopix.dto.DonatarioDto;
import com.doacao.pix.doacaopix.service.DonatarioService;
import com.doacao.pix.doacaopix.utils.NegocioException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(path = "/donatario")
public class DonatarioController {

    private final DonatarioService donatarioService;

    public DonatarioController(DonatarioService doadorService) {
        this.donatarioService = doadorService;
    }

    @PostMapping
    public ResponseEntity<DonatarioDto> save(@Valid @RequestBody DonatarioDto donatarioDto) {
        return ResponseEntity.ok()
                .body(this.donatarioService.salvar(donatarioDto));
    }

    @PutMapping
    public ResponseEntity<DonatarioDto> update(@Valid @RequestBody DonatarioDto donatarioDto) {
        return ResponseEntity.ok()
                .body(this.donatarioService.atualizar(donatarioDto));
    }

    @DeleteMapping(path = "/{codigo}")
    public ResponseEntity<?> delete(@NotNull(message = "Codigo no path é obrigatório") @PathVariable Long codigo) {
        this.donatarioService.excluir(codigo);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{codigo}")
    public ResponseEntity<DonatarioDto> findById(@NotNull(message = "Codigo no path é obrigatório") @PathVariable Long codigo) {
        DonatarioDto donatarioDto = this.donatarioService.buscarPorCodigo(codigo);
        return donatarioDto != null ? ResponseEntity.ok(donatarioDto) : ResponseEntity.noContent().build();
    }
}
