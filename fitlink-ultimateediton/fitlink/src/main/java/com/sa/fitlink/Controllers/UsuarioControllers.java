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


import com.sa.fitlink.Entities.Usuario;
import com.sa.fitlink.Services.UsuarioService;

@RestController
@RequestMapping("users")
@CrossOrigin
public class UsuarioControllers {
    @Autowired
    private UsuarioService service;
    
    @CrossOrigin(origins = "http://localhost:3000/")
    @PostMapping
    public ResponseEntity<Usuario> post(@RequestBody Usuario user){
        Usuario usuarioCriado = service.create(user);
        return new ResponseEntity<Usuario>(usuarioCriado, HttpStatus.CREATED);
    }
  

    @PutMapping
    public ResponseEntity<Usuario> put(@RequestBody Usuario user){
        Usuario UsuarioAtualizado = service.update(user);
        return ResponseEntity.ok(UsuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getList(){
        List<Usuario> lista = service.list();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getRead(@PathVariable Long id){
       Usuario usuarioEncontrado = service.read(id);
        return ResponseEntity.ok(usuarioEncontrado);
    }


    
}
