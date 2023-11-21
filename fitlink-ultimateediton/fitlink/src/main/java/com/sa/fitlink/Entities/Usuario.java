package com.sa.fitlink.Entities;

import java.util.Collection;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data @AllArgsConstructor @NoArgsConstructor
public class Usuario implements UserDetails{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    @Column(unique = true)
    private String username;

    private String date;
    
    public Usuario(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @OneToMany(mappedBy = "usuario")
    private List<Amigo> amigos;
    
    @OneToMany(mappedBy = "usuario")
    private List<Foto> fotos;

    @OneToMany(mappedBy = "usuario")
    private List<Publicacao> publicacoes;


    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

  
}
