package com.sa.fitlink.Tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.sa.fitlink.Entities.Publicacao;
import com.sa.fitlink.Entities.Usuario;
import com.sa.fitlink.Repositories.PublicacaoRepositories;
import com.sa.fitlink.Services.PublicacaoService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PublicacaoServiceTest {

    @InjectMocks
    private PublicacaoService publicacaoService;

    @Mock
    private PublicacaoRepositories repository;

    @Test
    void testCreate() {
        // Cenário 1: Publicacao válida
        Publicacao publicacaoValida = new Publicacao(1L, "Legenda válida", "2023-01-01", 1, new Usuario());
        when(repository.save(publicacaoValida)).thenReturn(publicacaoValida);

        Publicacao publicacaoCriada = publicacaoService.create(publicacaoValida);
        assertEquals(publicacaoValida, publicacaoCriada);

        // Cenário 2: Legenda inválida
        Publicacao publicacaoComLegendaInvalida = new Publicacao(2L, "Le", "2023-01-01", 1, new Usuario());
        assertThrows(IllegalArgumentException.class, () -> publicacaoService.create(publicacaoComLegendaInvalida));
    }

    @Test
    void testRead() {
        // Cenário 1: Publicacao existente
        Publicacao publicacaoExistente = new Publicacao(1L, "Legenda", "2023-01-01", 1, new Usuario());
        when(repository.findById(1L)).thenReturn(Optional.of(publicacaoExistente));

        Publicacao resultado = publicacaoService.read(1L);
        assertEquals(publicacaoExistente, resultado);

        // Cenário 2: Publicacao inexistente
        when(repository.findById(2L)).thenReturn(Optional.empty());

        Publicacao resultadoNulo = publicacaoService.read(2L);
        assertNull(resultadoNulo);
    }

    @Test
    void testList() {
        // Cenário 1: Lista de Publicacoes válida
        List<Publicacao> publicacoesFicticias = Arrays.asList(
            new Publicacao(1L, "Legenda1", "2023-01-01", 1, new Usuario()),
            new Publicacao(2L, "Legenda2", "2023-02-01", 2, new Usuario())
        );
        when(repository.findAll()).thenReturn(publicacoesFicticias);

        List<Publicacao> resultado = publicacaoService.list();
        assertEquals(publicacoesFicticias, resultado);

        // Cenário 2: Lista de Publicacoes vazia
        when(repository.findAll()).thenReturn(Arrays.asList());

        List<Publicacao> resultadoVazio = publicacaoService.list();
        assertTrue(resultadoVazio.isEmpty());
    }

    @Test
    void testDelete() {
        
        publicacaoService.delete(1L);
    

    }
    
    
    
    @Test
    void testDeletePublicacaoInexistente() {
        
        when(repository.findById(2L)).thenReturn(Optional.empty());
    
     
        assertThrows(IllegalArgumentException.class, () -> publicacaoService.delete(2L));
    

        verify(repository, times(1)).findById(2L);

    }
    

    @Test
    void testUpdate() {
       
        Publicacao publicacaoExistente = new Publicacao(1L, "Legenda", "2023-01-01", 1, new Usuario());
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(publicacaoExistente)).thenReturn(publicacaoExistente);

        Publicacao resultado = publicacaoService.update(publicacaoExistente);
        assertEquals(publicacaoExistente, resultado);
        verify(repository, times(1)).save(publicacaoExistente);

   
        Publicacao publicacaoInexistente = new Publicacao(2L, "Legenda2", "2023-02-01", 2, new Usuario());
        when(repository.existsById(2L)).thenReturn(false);

        Publicacao resultadoNulo = publicacaoService.update(publicacaoInexistente);
        assertNull(resultadoNulo);
        verify(repository, never()).save(publicacaoInexistente);
    }
}
