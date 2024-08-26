package com.tienda.service;

import com.tienda.domain.Categoria;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UsuarioDetailsService {
    
    public UserDetails
         loadUserByUsername(String username)
            throws
            UsernameNotFoundException;
    

    
    
    
    
    
    
}
