package com.example.mascotas;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/vendedores")
public class VendedoresController {

    private List<Vendedor> vendedores = new ArrayList<>();

    public VendedoresController() {
        vendedores.add(new Vendedor(1, "Ana Torres", "ana@mascotas.cl"));
        vendedores.add(new Vendedor(2, "Luis Pérez", "luis@mascotas.cl"));
        vendedores.add(new Vendedor(3, "Carla Gómez", "carla@mascotas.cl"));
        vendedores.add(new Vendedor(4, "Pablo Marin", "pablo@mascotas.cl"));
        vendedores.add(new Vendedor(5, "Cristian Rojas", "cristian@mascotas.cl"));
        vendedores.add(new Vendedor(6, "Fernanda Bermedo", "fernanda@mascotas.cl"));
        vendedores.add(new Vendedor(7, "Maria Sepulveda", "maria@mascotas.cl"));
        vendedores.add(new Vendedor(8, "José Pereira", "jose@mascotas.cl"));

    }

    @GetMapping
    public List<Vendedor> listar() {
        return vendedores;
    }

    // GET http://localhost:8080/vendedores

    @GetMapping("/{id}")
    public Vendedor obtenerPorId(@PathVariable int id) {
        return vendedores.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // GET http://localhost:8080/vendedores/3

}
