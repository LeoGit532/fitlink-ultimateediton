package com.sa.fitlink.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sa.fitlink.Entities.Publicacao;
import com.sa.fitlink.Services.PublicacaoService;

@RestController
@RequestMapping("/publishers")
@CrossOrigin("*")
public class PublicacaoControllers {
    @Autowired
    private PublicacaoService service;

   @PostMapping
    public ResponseEntity<Publicacao> post(@RequestBody Publicacao Publicacoo){
        Publicacao PublicacaoCriado = service.create(Publicacoo);
        return new ResponseEntity<>(PublicacaoCriado, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Publicacao> put(@RequestBody Publicacao Publicacao){
        Publicacao PublicacaoAtualizado = service.update(Publicacao);
        return ResponseEntity.ok( PublicacaoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Publicacao>> getList(){
        List<Publicacao> lista = service.list();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publicacao> getRead(@PathVariable Long id){
        Publicacao PublicacaoEncontrado = service.read(id);
        return ResponseEntity.ok(PublicacaoEncontrado);
    }

    
}
