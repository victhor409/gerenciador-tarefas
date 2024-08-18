package com.gerenciador.tarefaz.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gerenciador.tarefaz.Service.AutenticacaoService;
import com.gerenciador.tarefaz.entities.UsuarioAutenticacao;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

public class LoginFiltro extends AbstractAuthenticationProcessingFilter {

    public LoginFiltro(String url, AuthenticationManager authenticationManager){
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        String collect = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        UsuarioAutenticacao usuarioAutenticacao = new ObjectMapper().readValue(collect, UsuarioAutenticacao.class);

        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                usuarioAutenticacao.getName(),
                usuarioAutenticacao.getPassword(),
                Collections.emptyList()
        ));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authentication);

        AutenticacaoService.addJWTToken(response, authentication.getName());


    }
}
