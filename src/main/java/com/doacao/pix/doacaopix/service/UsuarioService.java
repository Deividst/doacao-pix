package com.doacao.pix.doacaopix.service;

import com.doacao.pix.doacaopix.model.Usuario;
import com.doacao.pix.doacaopix.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario buscarPorNome(String nome){
        return this.usuarioRepository.findByNome(nome);
    }
}
