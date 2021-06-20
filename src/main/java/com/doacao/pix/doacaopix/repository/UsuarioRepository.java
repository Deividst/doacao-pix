package com.doacao.pix.doacaopix.repository;

import com.doacao.pix.doacaopix.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByNome(String nome);
}
