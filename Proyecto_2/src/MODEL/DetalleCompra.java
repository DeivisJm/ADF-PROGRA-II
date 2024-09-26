package MODEL;

import GUI.*;

public class DetalleCompra {

    private int Id_DetalleCompra;
    private int cantidad;
    private double monto;
    private String idProducto;
    private int idCompra;
    private Administrador tico;
    private Usuario tico2;

    public DetalleCompra(Administrador tico) {
        this.tico = tico;
    }

    public DetalleCompra(Usuario tico2) {
        this.tico2 = tico2;
    }

    public DetalleCompra(int Id_DetalleCompra, int cantidad, double monto, String idProducto, int idCompra) {
        this.Id_DetalleCompra = Id_DetalleCompra;
        this.cantidad = cantidad;
        this.monto = monto;
        this.idProducto = idProducto;
        this.idCompra = idCompra;
    }

    public int getId_DetalleCompra() {
        return Id_DetalleCompra;
    }

    public void setId_DetalleCompra(int Id_DetalleCompra) {
        this.Id_DetalleCompra = Id_DetalleCompra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    /**
     * @return the idProducto
     */
    public String getIdProducto() {
        return idProducto;
    }

    /**
     * @param idProducto the idProducto to set
     */
    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * @return the idCompra
     */
    public int getIdCompra() {
        return idCompra;
    }

    /**
     * @param idCompra the idCompra to set
     */
    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

}
