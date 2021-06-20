package com.doacao.pix.doacaopix.repository;

import com.doacao.pix.doacaopix.enums.StatusDoacao;
import com.doacao.pix.doacaopix.model.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoacaoRepository extends JpaRepository<Doacao, Long> {

    List<Doacao> findByStatusDoacao(StatusDoacao statusDoacao);
}
