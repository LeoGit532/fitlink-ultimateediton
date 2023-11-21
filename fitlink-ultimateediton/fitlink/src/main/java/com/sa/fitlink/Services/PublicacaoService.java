package com.sa.fitlink.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.fitlink.Entities.Publicacao;
import com.sa.fitlink.Repositories.PublicacaoRepositories;

@Service
public class PublicacaoService {

    private  PublicacaoRepositories repository;

    @Autowired
    public PublicacaoService(PublicacaoRepositories repository) {
        this.repository = repository;
    }

    @Transactional
    public Publicacao create(Publicacao publicacao) {
        if (publicacao.getLegenda() == null || publicacao.getLegenda().length() < 3) {
            throw new IllegalArgumentException("A legenda deve ter pelo menos 3 caracteres");
        }
    
    
    
        Publicacao publicacaoCriada = repository.save(publicacao);
        return publicacaoCriada;

    }

    public Publicacao read(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Publicacao> list() {
        return (List<Publicacao>) repository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        System.out.println("Deletando Publicacao com ID: " + id);
        Optional<Publicacao> optionalPublicacao = repository.findById(id);
    
        if (optionalPublicacao.isPresent()) {
            repository.deleteById(id);
        } else {
            System.out.println("A publicação com o ID " + id + " não existe. Nenhuma ação foi realizada.");
        }
    }
    
    

    @Transactional
    public Publicacao update(Publicacao publicacao) {
        if (repository.existsById(publicacao.getId())) {
            return repository.save(publicacao);
        } else {
            return null;
        }
    }
}
