package com.sa.fitlink.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.fitlink.Entities.Usuario;
import com.sa.fitlink.Repositories.UsuarioRepositories;

@Service
public class UsuarioService implements UserDetailsService{
    @Autowired
    private UsuarioRepositories repository;

    @Transactional
    public Usuario create(Usuario user){
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(password);
        Usuario userCriado = repository.save(user);
        return userCriado;
    }

    public Usuario read(Long id){
        return repository.findById(id).get();
    }

    public List<Usuario> list(){
        List<Usuario> users = (List<Usuario>) repository.findAll();
        return users;
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    @Transactional
    public Usuario update(Usuario user){
        if(repository.existsById(user.getId())){
            return repository.save(user);
        }else{
            return null;
        }
    }
       @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = repository.findByUsername(username);
        if(user != null){
            return org.springframework.security.core.userdetails.User.builder()
                .password(user.getPassword())
                .username(user.getUsername())
            .build();
        }else{
            throw new UsernameNotFoundException("");
        }
    }
}
