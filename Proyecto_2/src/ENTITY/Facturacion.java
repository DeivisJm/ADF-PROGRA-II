package ENTITY;

import GUI.*;

/**
 *
 * @author andrew
 */

public class Facturacion {

    private String nombre;
    private String peso;
    private int cantidad;
    private double precio;
    private double subTotal;
    private StringBuilder FacturaProducto = new StringBuilder();
    private Usuario usuario;

    public Facturacion(Usuario usuario) {
        this();
        this.usuario = usuario;
    }

    public Facturacion(String nombre, String peso, int cantidad, double precio, double subTotal) {
        this.nombre = nombre;
        this.peso = peso;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subTotal = subTotal;
        this.FacturaProducto = new StringBuilder();
    }

    // Constructor sin argumentos
    public Facturacion() {
        this.nombre = "";
        this.peso = "";
        this.cantidad = 0;
        this.precio = 0.0;
        this.subTotal = 0.0;
        this.FacturaProducto = new StringBuilder();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public StringBuilder getFacturaProducto() {
        return FacturaProducto;
    }

    public void setFacturaProducto(StringBuilder FacturaProducto) {
        this.FacturaProducto = FacturaProducto;
    }

}
