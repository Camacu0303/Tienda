package com.tienda.service;

import com.tienda.domain.Producto;
import java.util.List;

public interface ProductoService {

        public List<Producto> getProductos(boolean activos);

    //Se define la firma del metodo para recuperar Un registro de 
    //la tabla producto recuperando el registro que tiene el 
    //idProducto , si no lo encuentra retorna null
    
    public Producto getProducto(Producto producto);

    //Se define la firma del metodo para crear o actualizar Un registro de 
    //la tabla producto si el idProducto no tiene valor , se crea el registro 
    //si tiene valor entonces modifica ese registro
    public void save(Producto producto);

    //Se define la firma del metodo para eliminar Un registro de 
    //la tabla producto, consideran id producto
    public void delete(Producto producto);

    //Consulta JPQL
   public List<Producto> consultaJPA(double precioInf, double precioSup);
   
    public List<Producto> consultaJPA_Existencias(int cantidadMinima);

    public List<Producto> consultaJPQL(double precioInf, double precioSup);

    public List<Producto> consultaSQL(double precioInf, double precioSup);

    
    public List<Producto> consultaSQLExistenciasMinimas(int cantidadMinima);
}
