package com.gerenciador.tarefaz.Service;

import com.gerenciador.tarefaz.Repository.IUsuarioRepository;
import com.gerenciador.tarefaz.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioAutenticacoService {

    @Autowired
    private IUsuarioRepository repository;

    public UserDetails loadUserByUsername(String name){

        Usuario usuario = repository.findbyUsername(name)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario "+name+"não foi encontrado"));

        List<SimpleGrantedAuthority> roles =
                usuario.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getNome()))
                        .collect(Collectors.toList());

        return new User(usuario.getUserName(), usuario.getPassword(),roles);
    }

}
