package com.example.mascotas;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductosController {

    private final ProductosRepository repository;

    public ProductosController(ProductosRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Productos> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Productos crear(@RequestBody Productos producto) {
        return repository.save(producto);
    }
}
