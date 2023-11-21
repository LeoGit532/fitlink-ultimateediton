package com.sa.fitlink.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.security.core.userdetails.UserDetails;
import com.sa.fitlink.Entities.Usuario;

public interface UsuarioRepositories extends JpaRepository<Usuario, Long> {

    public Usuario findByUsername(String username);
    
}

