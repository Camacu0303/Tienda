package com.tienda.service.impl;

import com.tienda.dao.ProductoDao;
import com.tienda.domain.Producto;
import com.tienda.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoDao productoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> getProductos(boolean activos) {
        var lista = productoDao.findAll();

        if (activos) {
            lista.removeIf(c -> !c.isActivo());
        }
        return lista;
    }
    
    
    
    @Override
    @Transactional(readOnly = true)
    public Producto getProducto(Producto producto) {
        return productoDao.findById(producto.getIdProducto()).orElse(null);
    }

    
    
    
    
    
    
    
    @Override
    @Transactional
    public void save(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    @Transactional
    public void delete(Producto producto) {
        productoDao.delete(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> consultaJPA(double precioInf, double precioSup) {
        return productoDao.findByPrecioBetweenOrderByPrecio(precioInf, precioSup);

    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Producto> consultaJPA_Existencias(int cantidadMinima) {
        return productoDao.findByExistenciasGreaterThanEqual(cantidadMinima);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> consultaJPQL(double precioInf, double precioSup) {
        return productoDao.consultaJPQL(precioInf, precioSup);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> consultaSQL(double precioInf, double precioSup) {
        return productoDao.consultaSQL(precioInf, precioSup);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> consultaSQLExistenciasMinimas(int cantidadMinima) {
        
        return productoDao.consultaSQLExistenciasMinimas(cantidadMinima);
        
        
    }
    
    
}
