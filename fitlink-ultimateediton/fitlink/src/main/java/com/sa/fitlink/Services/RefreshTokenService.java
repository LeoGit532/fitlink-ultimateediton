package com.sa.fitlink.Services;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.fitlink.Entities.RefreshToken;
import com.sa.fitlink.Entities.Usuario;
import com.sa.fitlink.Repositories.RefreshTokenRepositories;
import com.sa.fitlink.Repositories.UsuarioRepositories;

@Service
public class RefreshTokenService {
    @Value("${api.security.refresh.expiration_sec}")
    private String refreshTokenDurationS;
    @Autowired
    private RefreshTokenRepositories refreshTokenRepository;
    @Autowired
    private UsuarioRepositories userRepository;

    public RefreshToken createRefreshToken(UserDetails userDetails, String token) {
        RefreshToken refreshToken = new RefreshToken();
        var user = userRepository.findByUsername(userDetails.getUsername());
        refreshToken.setUser(user);
        refreshToken.setExpirationTime(
                Instant.now().plusSeconds(Long.parseLong(refreshTokenDurationS)));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setToken(token);

        refreshToken = refreshTokenRepository.save(refreshToken);
        try {
            this.deleteByUser(user);
        } catch (Exception e) {
        }

        return refreshToken;
    }

    public Optional<RefreshToken> findByToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpirationTime().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException(
                    "Refresh token was expired. Please make a new signin request");
        }
        return token;
    }

    @Transactional
    public int deleteByUser(Usuario user) {
        return refreshTokenRepository.deleteByUser(user);
    }

}