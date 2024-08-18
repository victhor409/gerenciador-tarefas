package com.gerenciador.tarefaz.config;

import com.gerenciador.tarefaz.Service.UsuarioAutenticacoService;
import com.gerenciador.tarefaz.filter.AutenticacaoFiltro;
import com.gerenciador.tarefaz.filter.LoginFiltro;
import com.gerenciador.tarefaz.permissoes.PermissaoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UsuarioAutenticacoService service;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers("/login").permitAll()
                            .requestMatchers(HttpMethod.GET,"/teste").permitAll()
                            .requestMatchers(HttpMethod.GET,"/teste2").hasAnyAuthority(PermissaoEnum.ADMINISTRADOR.toString())
                            .requestMatchers(HttpMethod.GET,"/usuarios").hasAnyAuthority(PermissaoEnum.USUARIO.toString())
                            .anyRequest().authenticated();
                });

        http.addFilterBefore(new LoginFiltro("/login", authenticationConfiguration.getAuthenticationManager()), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new AutenticacaoFiltro(),  UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }






}
