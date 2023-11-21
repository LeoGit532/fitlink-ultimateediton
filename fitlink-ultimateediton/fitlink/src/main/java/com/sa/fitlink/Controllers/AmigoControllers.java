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

import com.sa.fitlink.Entities.Amigo;
import com.sa.fitlink.Services.AmigoService;

@RestController
@CrossOrigin("*")
@RequestMapping("/friends")
public class AmigoControllers {
    @Autowired
    private AmigoService service;

   @PostMapping
    public ResponseEntity<Amigo> post(@RequestBody Amigo Amigo){
        Amigo AmigoCriado = service.create(Amigo);
        return new ResponseEntity<>(AmigoCriado, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Amigo> put(@RequestBody Amigo Amigo){
        Amigo AmigoAtualizado = service.update(Amigo);
        return ResponseEntity.ok(AmigoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Amigo>> getList(){
        List<Amigo> lista = service.list();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Amigo> getRead(@PathVariable Long id){
       Amigo amigoEncontrado = service.read(id);
        return ResponseEntity.ok(amigoEncontrado);
    }

    
}
