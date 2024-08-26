package com.tienda.service;

import com.tienda.domain.Categoria;
import java.util.List;

public interface CategoriaService {
        public List<Categoria> getCategorias(boolean activos);
    
    //Se define la firma del metodo para recuperar Un registro de 
    //la tabla categoria recuperando el registro que tiene el 
    //idCategoria , si no lo encuentra retorna null
    
    public Categoria getCategoria(Categoria categoria);
    
       //Se define la firma del metodo para crear o actualizar Un registro de 
    //la tabla categoria si el idCategoria no tiene valor , se crea el registro 
    //si tiene valor entonces modifica ese registro
    
    public void save(Categoria categoria);
    
    
    
    
      //Se define la firma del metodo para eliminar Un registro de 
    //la tabla categoria, consideran id categoria
    
    public void delete(Categoria categoria);
    
    

    
    
    
    
    
    
}
