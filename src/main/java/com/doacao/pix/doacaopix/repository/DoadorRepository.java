package com.doacao.pix.doacaopix.repository;

import com.doacao.pix.doacaopix.model.Doador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoadorRepository extends JpaRepository<Doador, Long> {
}
