package com.sa.fitlink.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.sa.fitlink.Entities.RefreshToken;
import com.sa.fitlink.Entities.Usuario;

public interface RefreshTokenRepositories extends JpaRepository<RefreshToken, Long> {
   Optional<RefreshToken> findByToken(String token);
   Optional<RefreshToken> findByRefreshToken(String refreshToken);
   @Modifying
   int deleteByUser(Usuario user);
}