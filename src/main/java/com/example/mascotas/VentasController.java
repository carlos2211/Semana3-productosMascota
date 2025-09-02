package com.example.mascotas;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/ventas")
public class VentasController {

    private List<Ventas> ventas = new ArrayList<>();
    private List<Productos> productos;

    public VentasController(ProductosController productosController) {

        this.productos = productosController.getAll();
    }

    // Registrar una nueva venta
    @PostMapping
    public String registrarVenta(@RequestParam int productoId, @RequestParam int cantidad) {
        Optional<Productos> productoOpt = productos.stream()
                .filter(p -> p.getId() == productoId)
                .findFirst();

        // POST http://localhost:8080/ventas?productoId=1&cantidad=2

        if (productoOpt.isEmpty()) {
            return "❌ Producto no encontrado";
        }

        Productos producto = productoOpt.get();

        if (producto.getStock() < cantidad) {
            return "⚠️ No hay suficiente stock disponible";
        }

        // Actualizar stock
        int nuevoStock = producto.getStock() - cantidad;

        producto.setStock(nuevoStock);

        // Calcular total
        int total = cantidad * producto.getValor();

        // Crear y guardar la venta
        Ventas venta = new Ventas(ventas.size() + 1, productoId, cantidad, total);
        ventas.add(venta);

        return " Venta registrada: " + cantidad + " x " + producto.getNombre_producto() +
                " | Total = $" + total;
    }

    // Listar todas las ventas
    @GetMapping
    public List<Ventas> listarVentas() {
        return ventas;
    }

    // GET http://localhost:8080/ventas

    // Buscar venta por ID
    @GetMapping("/{id}")
    public Ventas obtenerVenta(@PathVariable int id) {
        return ventas.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // GET http://localhost:8080/ventas/1
}
