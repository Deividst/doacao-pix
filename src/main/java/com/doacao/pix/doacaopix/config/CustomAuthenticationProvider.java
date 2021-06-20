package com.doacao.pix.doacaopix.config;

import com.doacao.pix.doacaopix.model.Usuario;
import com.doacao.pix.doacaopix.service.UsuarioService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UsuarioService usuarioService;

    public CustomAuthenticationProvider(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String user = authentication.getName();
        String password = authentication.getCredentials().toString();

        Usuario usuario = this.usuarioService.buscarPorNome(user);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("DONATARIO"));

        if (Objects.isNull(usuario) || !usuario.getNome().equals(user) && !usuario.getSenha().equals(password)) {
            throw new UsernameNotFoundException("Usuário e/ou senha incorretos");
        }

        return new UsernamePasswordAuthenticationToken(usuario.getNome(), usuario.getSenha(), authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}