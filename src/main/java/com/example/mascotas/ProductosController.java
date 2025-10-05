package com.example.mascotas;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/productos")
public class ProductosController {

    private final ProductosRepository repository;

    public ProductosController(ProductosRepository repository) {
        this.repository = repository;
    }

    // GET - Listar todos con HATEOAS
    @GetMapping
    public CollectionModel<EntityModel<Productos>> listar() {
        List<EntityModel<Productos>> productos = repository.findAll().stream()
                .map(p -> EntityModel.of(p,
                        linkTo(methodOn(ProductosController.class).obtenerPorId(p.getId())).withSelfRel(),
                        linkTo(methodOn(ProductosController.class).listar()).withRel("todos")))
                .toList(); // 

        return CollectionModel.of(productos,
                linkTo(methodOn(ProductosController.class).listar()).withSelfRel());
    }

    // GET - Obtener uno por ID con HATEOAS
    @GetMapping("/{id}")
    public EntityModel<Productos> obtenerPorId(@PathVariable Integer id) {
        Productos producto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id " + id));

        return EntityModel.of(producto,
                linkTo(methodOn(ProductosController.class).obtenerPorId(id)).withSelfRel(),
                linkTo(methodOn(ProductosController.class).listar()).withRel("todos"));
    }

    // POST - Crear un producto
    @PostMapping
    public Productos crear(@RequestBody Productos producto) {
        return repository.save(producto);
    }
}
