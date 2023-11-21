package com.sa.fitlink.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.fitlink.Entities.Amigo;
import com.sa.fitlink.Repositories.AmigoRepositories;

@Service
public class AmigoService {
    @Autowired
    private AmigoRepositories repository;

    @Transactional
    public Amigo create(Amigo friend) {
   
        if (friend.getData_amizade() == null) {
        
            throw new IllegalArgumentException("A data de amizade não pode ser nula");
        }

     
        if (friend.getUsuario() == null) {
   
            throw new IllegalArgumentException("O usuário associado ao amigo não pode ser nulo");
        }

        Amigo amigoCriado = repository.save(friend);
        return amigoCriado;
    }
    public Amigo read(Long id){
        return repository.findById(id).get();
    }

    public List<Amigo> list(){
        List<Amigo> friends = (List<Amigo>) repository.findAll();
        return friends;
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    @Transactional
    public Amigo update(Amigo friend){
        if(repository.existsById(friend.getId())){
            return repository.save(friend);
        }else{
            return null;
        }
    }
    
}
