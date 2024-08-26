package com.tienda.controller;

import com.tienda.domain.Categoria;
import com.tienda.domain.Producto;
import com.tienda.service.CategoriaService;
import com.tienda.service.ProductoService;
import com.tienda.service.FirebaseStorageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/pruebas")
public class PruebasController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProductoService productoService;

    @GetMapping("/listado")
    public String listado(Model model) {

        var lista = productoService.getProductos(false);

        model.addAttribute("productos", lista);

        var listaCategorias = categoriaService.getCategorias(true);
        model.addAttribute("categorias", listaCategorias);

        return "/pruebas/listado";
    }

    @GetMapping("/listado/{idCategoria}")
    public String save(Categoria categoria, Model model) {
        categoria = categoriaService
                .getCategoria(categoria);
        var lista = categoria.getProductos();
        model.addAttribute("productos", lista);

        return "pruebas/listado";

    }

    @GetMapping("/listado2")
    public String listado2(Model model) {

        var lista = productoService.getProductos(false);

        model.addAttribute("productos", lista);

        return "/pruebas/listado2";
    }

    @PostMapping("/query1")
    public String consultaJPA(
            @RequestParam("precioInf") double precioInf,
            @RequestParam("precioSup") double precioSup,
            Model model) {
        var productos = productoService.consultaJPA(precioInf, precioSup);
        model.addAttribute("productos", productos);
        model.addAttribute("precioSup", precioSup);
        model.addAttribute("precioInf", precioInf);

        return "/pruebas/listado2";

    }

    @PostMapping("/query2")
    public String consultaJPQL(
            @RequestParam("precioInf") double precioInf,
            @RequestParam("precioSup") double precioSup,
            Model model) {
        var productos = productoService.consultaJPQL(precioInf, precioSup);
        model.addAttribute("productos", productos);
        model.addAttribute("precioSup", precioSup);
        model.addAttribute("precioInf", precioInf);

        return "/pruebas/listado2";
    }

    @PostMapping("/query3")
    public String consultaSQL(
            @RequestParam("precioInf") double precioInf,
            @RequestParam("precioSup") double precioSup,
            Model model) {
        var productos = productoService.consultaSQL(precioInf, precioSup);
        model.addAttribute("productos", productos);
        model.addAttribute("precioSup", precioSup);
        model.addAttribute("precioInf", precioInf);

        return "/pruebas/listado2";

    }
    
    
    @GetMapping("/practica4")
    public String practica4(Model model) {

        var lista = productoService.getProductos(false);

        model.addAttribute("productos", lista);

        return "/pruebas/practica4";
    }
    
    
@PostMapping("/query4")
public String consultaSQL_Existencias(@RequestParam("existenciasMinimas") int existenciasMinimas, Model model) {
    // Utilizar el servicio para ejecutar la consulta SQL por existencias
    var productos = productoService.consultaSQLExistenciasMinimas(existenciasMinimas);

    // Agregar los resultados al modelo para pasar a la vista
    model.addAttribute("productos", productos);
    model.addAttribute("existenciasMinimas", existenciasMinimas);

    // Devolver la vista adecuada para mostrar los resultados
    return "/pruebas/practica4"; // Cambiar la vista si es necesario
}




}
    
    
    
    
    

    
    
    
    
    
    
    
    
    
    

