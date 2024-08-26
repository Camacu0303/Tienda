package com.tienda.service.impl;

import com.google.api.Http;
import com.tienda.dao.UsuarioDao;
import com.tienda.domain.Rol;
import com.tienda.domain.Usuario;
import com.tienda.service.UsuarioDetailsService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("userDetailsService")
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao; // Suponiendo que tienes un objeto UsuarioDao que maneja las operaciones de base de datos para usuarios.

    @Autowired
    private HttpSession session;
    
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) 
            throws UsernameNotFoundException {
        //Se busca el usuario que tiene el username
        Usuario usuario = usuarioDao.findByUsername(username);
       //Se debe validar si se encontro
        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }

        
        session.removeAttribute("usuarioImagen");
        session.setAttribute("usuarioImagen", usuario.getRutaImagen());
        
        
        var roles = new ArrayList<GrantedAuthority>();
        for (Rol r : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(r.getNombre()));
        }
        
        //Aca ya tenemos toda la info del usuario
        
        
        

        return new User(usuario.getUsername(),
                usuario.getPassword(),
                roles);
    }
}