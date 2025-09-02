package com.example.mascotas;

import java.time.LocalDateTime;

public class Ventas {
    private int id;
    private int productoId;
    private int cantidad;
    private int total;
    private LocalDateTime fecha;

    public Ventas(int id, int productoId, int cantidad, int total) {
        this.id = id;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.total = total;
        this.fecha = LocalDateTime.now();
    }

    public int getId() { return id; }
    public int getProductoId() { return productoId; }
    public int getCantidad() { return cantidad; }
    public int getTotal() { return total; }
    public LocalDateTime getFecha() { return fecha; }
}
