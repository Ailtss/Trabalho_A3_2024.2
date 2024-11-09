package com.example.crud.controllers;


import com.example.crud.domain.product.Product;
import com.example.crud.domain.usuario.RequestUsuario;
import com.example.crud.domain.usuario.Usuario;
import com.example.crud.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.Optional;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository ;


    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity getAllUsuarios() {

        var allUsuarios = repository.findAll();
        return ResponseEntity.ok(allUsuarios);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{email}")
    public ResponseEntity getUsuarioByEmail(@PathVariable String email) {

        Optional<Usuario> optionalUsuario = repository.findByEmail(email);

        if (optionalUsuario.isPresent()) {
            Usuario u = optionalUsuario.get();
            return ResponseEntity.ok(u);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("buscarPorJSON")
    public ResponseEntity getUsuarioByPassword(@RequestBody @Valid RequestUsuario data) {

        Optional<Usuario> optionalUsuario = repository.findByEmail(data.email());

        if (optionalUsuario.isPresent()) {

            Usuario u = optionalUsuario.get();
            if (u.getPassword().equals(data.password())) {

                return ResponseEntity.ok(u);
            } else {
                throw new EntityNotFoundException();
            }
        } else {
            throw new EntityNotFoundException();
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public ResponseEntity registerUsuario(@RequestBody @Valid RequestUsuario data) {
        Usuario newUsuario = new Usuario(data);
        repository.save(newUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUsuario);
    }
}
