package com.sa.fitlink.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.fitlink.Entities.Comentario;

public interface ComentarioRepositories extends JpaRepository<Comentario, Long> {
    
}
