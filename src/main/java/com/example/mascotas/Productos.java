package com.example.mascotas;

public class Productos {
    private int id;
    private String nombre_producto;
    private int stock;
    private int valor;
    private String descripcion;


    public Productos (int id, String nombre_producto, int stock, int valor, String descripcion) {
        this.id = id;
        this.nombre_producto = nombre_producto;
        this.stock = stock;
        this.valor = valor;
        this.descripcion = descripcion;
    }

    public int getId() {return id;}
    public String getNombre_producto() {return nombre_producto;}
    public int getStock() {return stock;}
    public int getValor() {return valor;}
    public String getDescripcion() {return descripcion;}

// SETTER necesario para actualizar stock
    public void setStock(int stock) {
        this.stock = stock;
    }

}
    
