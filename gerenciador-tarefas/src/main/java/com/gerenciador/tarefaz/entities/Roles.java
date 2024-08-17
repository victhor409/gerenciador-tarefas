package com.gerenciador.tarefaz.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class Roles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, length = 20)
    private String nome;

    @ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios;
}
