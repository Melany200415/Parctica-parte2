package com.practica.parte2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoRepository repository;
    public ProductoController(ProductoRepository repository) {
        this.repository = repository;
    }
    //Registrar
    @PostMapping
    public Producto addProducto(@RequestBody Producto producto) {
        return repository.save(producto);
    }
    //Consultar
    @GetMapping
    public List<Producto> findAll() {
        return repository.findAll();
    }
    //Consulta por id
    @GetMapping("/{id}")
    public Producto buscarProductoPorId(@PathVariable int id) {
        return repository.findById(id).orElse(null);
    }
    // Actualizar
    @PutMapping("/{id}")
    public Producto actualizarProducto
    (@RequestBody Producto producto, @PathVariable int id) {
        Producto existente = repository.findById(id).orElse(null);
        if (existente == null) {
            return null;
        }
        existente.setNombre(producto.getNombre());
        existente.setPrecio(producto.getPrecio());
        existente.setCantidad(producto.getCantidad());
        return repository.save(existente);
    }
    //eliminar
    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable int id) {
        repository.deleteById(id);
        return "Producto eliminado correctamenete";
    }

}
