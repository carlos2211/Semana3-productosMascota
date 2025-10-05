package com.example.mascotas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest // 🔹 Levanta solo la capa JPA con H2 en memoria
public class ProductosRepositoryTest {

    @Autowired
    private ProductosRepository repository;

    // 🔹 Test para guardar y recuperar un producto
    @Test
    void testGuardarYBuscarProducto() {
        Productos producto = new Productos(null, "Pelota", 3000, 5000, "Pelota de tenis");
        Productos guardado = repository.save(producto);

        Optional<Productos> resultado = repository.findById(guardado.getId());

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getNombre_producto()).isEqualTo("Pelota");
        assertThat(resultado.get().getStock()).isEqualTo(3000);
    }

    // 🔹 Test para listar productos
    @Test
    void testListarProductos() {
        repository.save(new Productos(null, "Collar", 10, 2000, "Collar rojo"));
        repository.save(new Productos(null, "Casa", 5, 10000, "Casa para perro"));

        List<Productos> lista = repository.findAll();

        assertThat(lista).hasSize(2);
        assertThat(lista.get(0).getNombre_producto()).isEqualTo("Collar");
        assertThat(lista.get(1).getNombre_producto()).isEqualTo("Casa");
    }
}
