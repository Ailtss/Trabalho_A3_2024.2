package com.example.crud.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record RequestUsuario(

        String id,


        String name,
        @NotBlank
        String email,
        @NotBlank
        String password) {
}
