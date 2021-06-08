package com.doacao.pix.doacaopix.repository;

import com.doacao.pix.doacaopix.model.Doador;
import com.doacao.pix.doacaopix.model.Donatario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonatarioRepository extends JpaRepository<Donatario, Long> {
}
