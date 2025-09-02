package com.example.mascotas;

import java.util.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productos")
public class ProductosController {

    private List<Productos> productos = new ArrayList<>();

    public ProductosController() {
        productos.add(new Productos(1, "Juguete", 10, 2000, "Juguete para perros"));
        productos.add(new Productos(2, "Comida", 20, 40000, "Comida para perros"));
        productos.add(new Productos(3, "Collar", 30, 6000, "Collar para perros"));
        productos.add(new Productos(4, "Jaula", 50, 50000, "Jaula para perros"));
        productos.add(new Productos(5, "Cama", 60, 32990, "Cama para perros"));
        productos.add(new Productos(6, "Juguete", 80, 4000, "Juguete para gatos"));
        productos.add(new Productos(7, "Comida", 90, 40000, "Comida para gatos"));
        productos.add(new Productos(8, "Collar", 100, 3500, "Collar para gatos"));
        productos.add(new Productos(9, "Jaula", 70, 70000, "Jaula para gatos"));
        productos.add(new Productos(10, "Cama", 90, 19000, "Cama para gatos"));
    }

    @GetMapping
    public List<Productos> getAll() {
        return productos;
    }

    @GetMapping("/{id}")
    public Productos obtenerPorId(@PathVariable int id) {
        return productos.stream()
                .filter(producto -> producto.getId() == id)
                .findFirst()
                .orElse(null);
    }
    // http://localhost:8080/productos/6


    @GetMapping("/stock/{id}")
    public int obtenerStock(@PathVariable int id) {
        return productos.stream()
                .filter(p -> p.getId() == id)
                .map(Productos::getStock)
                .findFirst()
                .orElse(0);
    }
    // http://localhost:8080/productos/stock/4

    @GetMapping("/buscar/{nombre}")
    public List<Productos> buscarPorNombre(@PathVariable String nombre) {
        return productos.stream()
                .filter(p -> p.getNombre_producto().toLowerCase().contains(nombre.toLowerCase()))
                .toList();
    }
    // http://localhost:8080/productos/buscar/comida
}
