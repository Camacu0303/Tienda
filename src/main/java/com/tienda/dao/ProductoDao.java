package com.tienda.dao;

import com.tienda.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductoDao extends JpaRepository<Producto, Long> {

    //Se define una consulta ampliada JPA para obtener la lista de Productos 
    //Que se encuentran en un rango de precios 
    public List<Producto> findByPrecioBetweenOrderByPrecio(double precioInf, double precioSup);
    
    public List<Producto> findByExistenciasGreaterThanEqual(int cantidadMinima);

    // Consulta nativa SQL para encontrar productos con existencias mayores o iguales a una cantidad mínima
    @Query(nativeQuery=true,
            value = "SELECT * FROM producto WHERE existencias >= :cantidadMinima")
    public List<Producto> consultaSQLExistenciasMinimas(int cantidadMinima);
    
    
    
    
    
    
    
    
    
    
    //Consulta JPQL
    @Query(value="SELECT p "
            + "FROM Producto p "
            + "WHERE p.precio "
            + "BETWEEN :precioInf and :precioSup "
            + "ORDER BY p.precio ASC")
    public List<Producto> consultaJPQL(double precioInf, double precioSup);
    
    
    
    

    //Consulta nativa SQL
    @Query(nativeQuery=true,
            value = "SELECT * "
            + "FROM producto p "
            + "WHERE p.precio "
            + "BETWEEN :precioInf AND :precioSup "
            + "ORDER BY p.precio ASC")
    public List<Producto> consultaSQL(double precioInf, double precioSup);

    

}
