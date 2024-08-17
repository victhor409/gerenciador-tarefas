package com.gerenciador.tarefaz.Service;

import com.gerenciador.tarefaz.Repository.IUsuarioRepository;
import com.gerenciador.tarefaz.entities.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository repository;

    @Transactional
    public Usuario salvarUsuario(Usuario usuario){
        return this.repository.save(usuario);
    }

    public Usuario atualizaUsuario(Usuario usuario){
        return this.repository.save(usuario);
    }

    public void excluirUsuario(Usuario usuario){
        this.repository.deleteById(usuario.getId());
    }

    public List<Usuario> obtemUsuarios(){
        return this.repository.findAll();
    }
}
