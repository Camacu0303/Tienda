package com.tienda.service;

import com.tienda.domain.Usuario;
import jakarta.mail.MessagingException;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface RegistroService {

    public Model activar(Model model, String usuario, String clave);

    public Model crearUsuario(Model model, Usuario usuario) throws MessagingException;//crea un usuario y envia un correo
    
    public void activar(Usuario usuario, MultipartFile imagenFile);//Envia un mensaje de activacion
    
    public Model recordarUsuario(Model model, Usuario usuario) throws MessagingException;//envia un correo de como puede ingresar
}
