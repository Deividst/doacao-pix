package com.doacao.pix.doacaopix.repository;

import com.doacao.pix.doacaopix.model.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoacaoRepository extends JpaRepository<Doacao, Long> {
}
