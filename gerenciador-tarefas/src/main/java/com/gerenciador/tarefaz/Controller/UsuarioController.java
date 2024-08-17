package com.gerenciador.tarefaz.Controller;

import com.gerenciador.tarefaz.Service.UsuarioService;
import com.gerenciador.tarefaz.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody  Usuario usuario){
        return new ResponseEntity<>(service.salvarUsuario(usuario), HttpStatus.OK);
    }
}
