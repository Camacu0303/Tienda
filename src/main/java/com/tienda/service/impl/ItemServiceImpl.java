package com.tienda.service.impl;

import com.tienda.dao.FacturaDao;
import com.tienda.dao.ProductoDao;
import com.tienda.dao.UsuarioDao;
import com.tienda.dao.VentaDao;
import com.tienda.domain.Factura;
import com.tienda.domain.Item;
import com.tienda.domain.Producto;
import com.tienda.domain.Usuario;
import com.tienda.domain.Venta;
import com.tienda.service.ItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Override
    public List<Item> gets() {
        return listaItems;
    }

    @Override
    public Item get(Item item) {

        for (Item i : listaItems) {
            if (i.getIdProducto() == item.getIdProducto()) {
                return i;
            }
        }

        return null;
    }

    @Override
    public void delete(Item item) {
        var posicion = -1;
        var existe = false;
        for (Item i : listaItems) {
            posicion++;

            if (i.getIdProducto() == item.getIdProducto()) {
                existe = true;
                break;
            }
        }
        if (existe) {
            listaItems.remove(posicion);
        }

    }

    @Override
    public void save(Item item) {

        var existe = false;
        for (Item i : listaItems) {
            if (i.getIdProducto() == item.getIdProducto()) {
                existe = true;
                if (i.getCantidad() < i.getExistencias()) {
                    i.setCantidad(i.getCantidad() + 1);
                }
                break;
            }
        }
        if (existe) {
            item.setCantidad(1);
            listaItems.add(item);
        }

    }

    @Override
    public void update(Item item) {
        for (Item i : listaItems) {
            if (i.getIdProducto() == item.getIdProducto()) {
                i.setCantidad(i.getCantidad());
                break;
            }
        }
    }

    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private ProductoDao productoDao;
    @Autowired
    private FacturaDao facturaDao;
    @Autowired
    private VentaDao ventaDao;
    
    
    
    
    @Override
    public void facturar() {
       //Se debe recuperar el usuario autenticado 
       
       String username;
       Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
               
       if (principal instanceof UserDetails userDetails) {
           username=userDetails.getUsername();
           
       }  else{
           
           username=principal.toString();
           
           
       }
       if (username.isBlank()){
           System.out.println("username en blanco...");
           return;
       }
       
       Usuario usuario =usuarioDao.findByUsername(username);
       if (usuario== null){
           System.out.println("Usuario no existe en usuarios...");
           return;
           
       }
       
       Factura factura = new Factura(usuario.getIdUsuario());
       facturaDao.save(factura);
       
       
       double total=0;
       for(Item i : listaItems){
           Venta venta = new Venta(factura.getIdFactura(),
           i.getIdProducto(),
           i.getPrecio(),
           i.getCantidad());
          ventaDao.save(venta);
       Producto producto = productoDao.getReferenceById(i.getIdProducto());
       producto.setExistencias(producto.getExistencias()-i.getCantidad());
       productoDao.save(producto);
           
       }
       
       factura.setTotal(total);
       facturaDao.save(factura);
       
       listaItems.clear();
       
       
       
    
    }

}
