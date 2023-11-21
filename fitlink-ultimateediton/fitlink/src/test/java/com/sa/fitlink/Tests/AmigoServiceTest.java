
package com.sa.fitlink.Tests;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;

import com.sa.fitlink.Entities.Amigo;
import com.sa.fitlink.Entities.Usuario;
import com.sa.fitlink.Repositories.AmigoRepositories;
import com.sa.fitlink.Services.AmigoService;

@SpringBootTest
class AmigoServiceTest {

    @InjectMocks
    private AmigoService amigoService;

    @Mock
    private AmigoRepositories repository;

    @Test
    void testCreate() {
       
    Amigo amigoComDataNula = new Amigo();
    assertThrows(IllegalArgumentException.class, () -> amigoService.create(amigoComDataNula), "Deveria lançar uma exceção para data_nula");


    
    Amigo amigoComUsuarioNulo = new Amigo();
    amigoComUsuarioNulo.setData_amizade("2023-01-01"); 
    assertThrows(IllegalArgumentException.class, () -> amigoService.create(amigoComUsuarioNulo));


    Amigo amigoValido = new Amigo();
    amigoValido.setData_amizade("2023-01-01"); 
    amigoValido.setUsuario(new Usuario()); 
    assertDoesNotThrow(() -> amigoService.create(amigoValido));
    }

    @Test
    void testRead() {
      
        List<Amigo> amigosFicticios = Arrays.asList(
            new Amigo(1L, "2023-01-01", new Usuario()),
            new Amigo(2L, "2023-02-01", new Usuario())
        );

        when(repository.findAll()).thenReturn(amigosFicticios);

        List<Amigo> resultado = amigoService.list();
        assertEquals(amigosFicticios, resultado);
    }

     @Test
    void testList() {
     
       List<Amigo> amigosFicticios = Arrays.asList(
        new Amigo(1L, "2023-01-01", new Usuario()),
        new Amigo(2L, "2023-02-01", new Usuario())
    );

    when(repository.findAll()).thenReturn(amigosFicticios);

    List<Amigo> resultado = amigoService.list();
    assertEquals(amigosFicticios, resultado);
    }

    @Test
    void testDelete() {
        Amigo amigoFicticio = new Amigo();
        amigoFicticio.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(amigoFicticio));

       
        amigoService.delete(1L);
        verify(repository, times(1)).deleteById(1L);
    }

   @Test
    void testUpdate() {
       
        Amigo amigoFicticio = new Amigo();
        amigoFicticio.setId(1L);

        
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(amigoFicticio)).thenReturn(amigoFicticio);

        
        Amigo resultado = amigoService.update(amigoFicticio);
        assertEquals(amigoFicticio, resultado);
        verify(repository, times(1)).save(amigoFicticio);
    }    
}
