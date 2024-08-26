package com.tienda.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Item extends Producto{

    private int cantidad;
    
    public Item(Producto p){
        super.setIdProducto(p.getIdProducto());
        super.setCategoria(p.getCategoria());
        super.setDescripcion(p.getDescripcion());
        super.setDetalle(p.getDetalle());
        super.setPrecio(p.getPrecio());
        super.setExistencias(p.getExistencias());
        super.setActivo(p.isActivo());
        super.setRutaImagen(p.getRutaImagen());
        this.cantidad=0;
    }
    
}
