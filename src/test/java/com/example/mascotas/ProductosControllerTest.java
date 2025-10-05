package com.example.mascotas;

// import com.example.mascotas.Productos;
// import com.example.mascotas.ProductosRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductosRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        repository.deleteAll();
    }

    @Test
    void testCrearProducto() throws Exception {
        Productos producto = new Productos(null, "Pelota", 3000, null, null);

        mockMvc.perform(post("/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre_producto", is("Pelota")))
                .andExpect(jsonPath("$.precio", is(3000)));
    }

    @Test
    void testObtenerProductoConHateoas() throws Exception {
        // Guardamos un producto en la BD de prueba
        Productos guardado = repository.save(new Productos(null, "Casa Grande Perro", 10000, null, null));

        mockMvc.perform(get("/productos/" + guardado.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre_producto", is("Casa")))
                .andExpect(jsonPath("$.precio", is(10000)))
                // Validar que tenga el objeto _links con self y todos
                .andExpect(jsonPath("$._links", hasKey("self")))
                .andExpect(jsonPath("$._links", hasKey("todos")));
    }
}
