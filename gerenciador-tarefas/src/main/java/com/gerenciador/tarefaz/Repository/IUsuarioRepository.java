package com.gerenciador.tarefaz.Repository;

import com.gerenciador.tarefaz.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findbyUsername(String username);
}
