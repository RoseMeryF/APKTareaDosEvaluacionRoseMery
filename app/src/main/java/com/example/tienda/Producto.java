package com.example.tienda;

public class Producto {
    int id;
    String producto;
    String descripcion;
    int precio;

    public Producto(int id, String producto, String descripcion, String precio) {
        this.id = id;
        this.producto = producto;
        this.descripcion = descripcion;
        this.precio = Integer.parseInt(String.valueOf(precio));
    }
    public Producto(String producto, String descripcion, int precio) {
        this.producto = producto;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() { return precio;}

    public void setPrecio(int precio) {
        this.precio = precio;
    }


}
