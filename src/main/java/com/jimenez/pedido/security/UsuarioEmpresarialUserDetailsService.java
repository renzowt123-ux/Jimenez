package com.jimenez.pedido.security;

import com.jimenez.pedido.repository.UsuarioEmpresarialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioEmpresarialUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UsuarioEmpresarialRepository repository;
    
    @Override
    public UserDetails loadUserByUsername(String ruc) throws UsernameNotFoundException {
        return repository.findByRuc(ruc)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + ruc));
    }
}
