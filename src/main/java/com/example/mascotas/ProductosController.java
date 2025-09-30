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

    // GET - Listar todos
    @GetMapping
    public List<Productos> listar() {
        return repository.findAll();
    }

    // GET - Obtener uno por ID
    @GetMapping("/{id}")
    public Productos obtenerPorId(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id " + id));
    }

    // POST - Crear un producto
    @PostMapping
    public Productos crear(@RequestBody Productos producto) {
        return repository.save(producto);
    }

    // ejemplo:
    // {
    // "nombre_producto": "Collar XL",
    // "stock": 40,
    // "valor": 6990,
    // "descripcion": "Collar reforzado para perro grande"
    // }

    // PUT - Actualizar un producto por ID
    @PutMapping("/{id}")
    public Productos actualizar(@PathVariable Integer id, @RequestBody Productos productoActualizado) {
        return repository.findById(id)
                .map(producto -> {
                    producto.setNombre_producto(productoActualizado.getNombre_producto());
                    producto.setStock(productoActualizado.getStock());
                    producto.setValor(productoActualizado.getValor());
                    producto.setDescripcion(productoActualizado.getDescripcion());
                    return repository.save(producto);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id " + id));
    }

    // ejemplo:
    // endpoint:PUT http://localhost:8080/productos/1
    // {
    // "nombre_producto": "Collar XL",
    // "stock": 40,
    // "valor": 6990,
    // "descripcion": "Collar reforzado para perro grande"
    // }

    // DELETE - Eliminar un producto por ID
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con id " + id);
        }
        repository.deleteById(id);
    }

    // ejemplo:
    // endpoint:DELETE http://localhost:8080/productos/1

}
