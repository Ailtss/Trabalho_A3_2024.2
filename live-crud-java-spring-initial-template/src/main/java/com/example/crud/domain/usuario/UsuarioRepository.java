package com.example.crud.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    @Query("SELECT u FROM usuario u WHERE u.password = :password")
    List<Usuario> findByPassword(@Param("password") String password);

    @Query("SELECT u FROM usuario u WHERE u.email = :email")
    Optional<Usuario> findByEmail(String email);


}
