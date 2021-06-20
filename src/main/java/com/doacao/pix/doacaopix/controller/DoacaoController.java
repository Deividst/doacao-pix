package com.doacao.pix.doacaopix.controller;

import com.doacao.pix.doacaopix.dto.DoacaoDto;
import com.doacao.pix.doacaopix.dto.DoadorDto;
import com.doacao.pix.doacaopix.service.DoacaoService;
import com.doacao.pix.doacaopix.utils.NegocioException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/doacao")
public class DoacaoController {

    private final DoacaoService doacaoService;

    public DoacaoController(DoacaoService doacaoService) {
        this.doacaoService = doacaoService;
    }

    @PostMapping("/{codigo}")
    public ResponseEntity<?> novaDoacao(@PathVariable Long codigo, @RequestBody DoacaoDto doacaoDto) {
        try {
            this.doacaoService.novaDoacao(codigo, doacaoDto);
        } catch (NegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/anonima")
    public ResponseEntity<DoadorDto> novaDoacaoAnonima(@RequestBody DoacaoDto doacaoDto) throws NegocioException {
        this.doacaoService.novaDoacao(null, doacaoDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/aprovar/{codigo}")
    public ResponseEntity<String> aprovarDoacao(@PathVariable Long codigo, @RequestParam String status){
        return ResponseEntity.ok().body(this.doacaoService.aprovarDoacao(codigo, status));
    }
}
