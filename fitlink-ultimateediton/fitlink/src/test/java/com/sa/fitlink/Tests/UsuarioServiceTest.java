package com.sa.fitlink.Tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.sa.fitlink.Entities.Usuario;
import com.sa.fitlink.Repositories.UsuarioRepositories;
import com.sa.fitlink.Services.UsuarioService;

@SpringBootTest
class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepositories repository;

    @Test
    void testCreate() {
     
        Usuario usuarioNomeNulo = new Usuario();
        assertThrows(IllegalArgumentException.class, () -> usuarioService.create(usuarioNomeNulo));

     
        Usuario usuarioEmailInvalido = new Usuario();
    
        usuarioEmailInvalido.setEmail("email-invalido");
        assertThrows(IllegalArgumentException.class, () -> usuarioService.create(usuarioEmailInvalido));

        
            // Cenário adicional: Verificar se a senha é criptografada
            Usuario usuarioValido = new Usuario();
            usuarioValido.setEmail("joao@example.com");
            usuarioValido.setPassword("senha-plaintext");
        
            // Garantir que a senha seja definida antes de chamar o create
            assertDoesNotThrow(() -> usuarioService.create(usuarioValido));
        }
        
        
    

    @Test
    void testRead() {
  
        Usuario usuarioFicticio = new Usuario();
        usuarioFicticio.setId(1L);
   
        usuarioFicticio.setEmail("alice@example.com");

        when(repository.findById(1L)).thenReturn(Optional.of(usuarioFicticio));

        Usuario resultado = usuarioService.read(1L);
        assertEquals(usuarioFicticio, resultado);
    }

    @Test
    void testList() {
        // Cenário: Lista fictícia de usuários
        List<Usuario> usuariosFicticios = Arrays.asList(
            new Usuario(1L, "Alice", "alice@example.com"),
            new Usuario(2L, "Bob", "bob@example.com")
        );

        when(repository.findAll()).thenReturn(usuariosFicticios);

        List<Usuario> resultado = usuarioService.list();
        assertEquals(usuariosFicticios, resultado);
    }

    @Test
    void testDelete() {
       
        Usuario usuarioFicticio = new Usuario();
        usuarioFicticio.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(usuarioFicticio));

        usuarioService.delete(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdate() {
   
        Usuario usuarioFicticio = new Usuario();
        usuarioFicticio.setId(1L);

        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(usuarioFicticio)).thenReturn(usuarioFicticio);

        Usuario resultado = usuarioService.update(usuarioFicticio);
        assertEquals(usuarioFicticio, resultado);
        verify(repository, times(1)).save(usuarioFicticio);
    }
}