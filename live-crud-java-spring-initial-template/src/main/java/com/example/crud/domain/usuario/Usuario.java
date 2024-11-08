package com.example.crud.domain.usuario;

import jakarta.persistence.*;
import lombok.*;
import org.apache.coyote.Request;

@Table(name="usuario")
@Entity(name="usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String email;

    private String password;


    public Usuario(RequestUsuario requestUsuario) {

        this.name = requestUsuario.name();
        this.email = requestUsuario.email();
        this.password = requestUsuario.password();
    }

}
