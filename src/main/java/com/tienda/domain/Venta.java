
package com.tienda.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;




@Data
@Entity
@Table (name="venta")
public class Venta implements  Serializable{
    private static final long serialVersionUID=1L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//La base de datos genera autmaticamente el IdVenta
    @Column(name="id_venta")
    private Long idVenta;
    private Long idFactura;
    private Long idProducto;
    private double precio;
    private int cantidad;

    public Venta() {
    }

    public Venta(Long idFactura, Long idProducto, double precio, int cantidad) {
        this.idFactura = idFactura;
        this.idProducto = idProducto;
        this.precio = precio;
        this.cantidad = cantidad;
    }
    

    
    
    
    
    
}


