package com.example.crud;


import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.crud.controllers.UsuarioController;
import com.example.crud.domain.usuario.RequestUsuario;
import com.example.crud.domain.usuario.Usuario;
import com.example.crud.domain.usuario.UsuarioRepository;
import org.apache.coyote.Request;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;


@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioRepository repository;

    @Test
    public void procurarUsuarioPorEmail() throws Exception {

        Usuario usuarioMock = new Usuario("Wagner", "wagner@wagner.com", "wagner12345");
        when(repository.findByEmail("wagner@wagner.com")).thenReturn(Optional.of(usuarioMock));

        mockMvc.perform(get("/usuario/wagner@wagner.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Wagner")))
                .andExpect(jsonPath("$.email", is("wagner@wagner.com")))
                .andExpect(jsonPath("$.password", is("wagner12345")));

        System.out.println("Teste de retorno realizado com sucesso");

    }

    @Test
    public void cadastrarUsuario() throws Exception {

        String usuarioJson = "{\"name\": \"Wagner\", \"email\": \"wagner@wagner.com\", \"password\": \"wagner12345\" }";

        mockMvc.perform(post("/usuario")
                        .contentType(APPLICATION_JSON)  // Define o tipo de conteúdo como JSON
                        .content(usuarioJson))  // Passa o corpo da requisição
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Wagner")))
                .andExpect(jsonPath("$.email", is("wagner@wagner.com")))
                .andExpect(jsonPath("$.password", is("wagner12345")));

        System.out.println("Teste de criação de usuário realizado com sucesso");
    }
}
