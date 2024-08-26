package com.tienda.controller;

import com.tienda.domain.Producto;
import com.tienda.service.CategoriaService;
import com.tienda.service.ProductoService;
import com.tienda.service.FirebaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProductoService productoService;

    @GetMapping("/listado")
    public String listado(Model model) {

        var lista = productoService.getProductos(false);

        model.addAttribute("productos", lista);
        model.addAttribute("totalProductos", lista.size());
        var listaCategorias = categoriaService.getCategorias(true);
        model.addAttribute("categorias", listaCategorias);

        return "/producto/listado";
    }

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @PostMapping("/guardar")
    public String guardar(Producto producto, @RequestParam("imagenFile") MultipartFile imagenFile) {

        if (!imagenFile.isEmpty()) { // Se debe subir una imagen
            //Primero se guarda la producto para obtener el idProducto nuevo 

            productoService.save(producto);

            String ruta = firebaseStorageService.cargaImagen(imagenFile, "producto", producto.getIdProducto());
            producto.setRutaImagen(ruta);
        }

        return "redirect:/producto/listado";

    }

    @GetMapping("/modificar/{idProducto}")
    public String modifica(Producto producto, Model model) {
        producto = productoService.getProducto(producto);
        model.addAttribute("producto", producto);
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
        return "producto/modifica";
    }
    
    
    
    
    @GetMapping("/eliminar/{idProducto}")
    public String elimina(Producto producto) {

        productoService.delete(producto);

        return "redirect:/producto/listado";

    }

}
