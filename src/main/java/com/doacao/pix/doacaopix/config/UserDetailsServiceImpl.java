package com.doacao.pix.doacaopix.config;

import com.doacao.pix.doacaopix.model.Usuario;
import com.doacao.pix.doacaopix.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.buscarPorNome(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(usuario);
    }

}