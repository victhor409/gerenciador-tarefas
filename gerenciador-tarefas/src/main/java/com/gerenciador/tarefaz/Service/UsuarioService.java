package com.gerenciador.tarefaz.Service;

import com.gerenciador.tarefaz.Repository.IUsuarioRepository;
import com.gerenciador.tarefaz.entities.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository repository;

    @Transactional
    public Usuario salvarUsuario(Usuario usuario){
        return this.repository.save(usuario);
    }
}
