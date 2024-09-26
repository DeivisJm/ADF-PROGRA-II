package MODEL;

import GUI.*;

public class Producto {

    private String idProducto;
    private String nombre;
    private double precio;
    private String idProveedor;
    private String idCategoria;
    private String idMarca;
    private String peso;
    private String imagen;
    private Administrador tico;

    public Producto(Administrador tico) {
        this.tico = tico;
    }

    public Producto(String idProducto, String nombre, double precio, String peso, String idProveedor, String idCategoria, String idMarca, String imagen) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.peso = peso;
        this.idProveedor = idProveedor;
        this.idCategoria = idCategoria;
        this.idMarca = idMarca;
        this.imagen = imagen;
    }

    public Producto(String nombre, double precio, String peso, String imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.peso = peso;
        this.imagen = imagen;
    }

    public Producto(String idProducto, String nombre, double precio, String peso, String idProveedor, String idCategoria, String idMarca) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.peso = peso;
        this.idProveedor = idProveedor;
        this.idCategoria = idCategoria;
        this.idMarca = idMarca;
    }

    public Producto(String nombre, double precio, String peso, String idProveedor, String idCategoria, String idMarca) {
        this.nombre = nombre;
        this.precio = precio;
        this.peso = peso;
        this.idProveedor = idProveedor;
        this.idCategoria = idCategoria;
        this.idMarca = idMarca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto() {
        this.idProducto = idProducto;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(String idMarca) {
        this.idMarca = idMarca;
    }

}
