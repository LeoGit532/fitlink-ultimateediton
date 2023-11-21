package com.sa.fitlink.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.fitlink.Entities.Publicacao;

public interface PublicacaoRepositories extends JpaRepository<Publicacao, Long> {

    boolean existsById(Long id);
    
}

