package ENTITY;

import GUI.*;

/**
 *
 * @author deivi
 */
public class Compra {

    private int idCompra;
    private String fecha;
    private double montoTotal;
    private int id_cliente;
    private int id_detalleCompra;
    private Usuario tico2;
    private Administrador tico;

    public Compra(Usuario tico2) {
        this.tico2 = tico2;
    }

    public Compra(Administrador tico) {
        this.tico = tico;
    }

    public Compra(int id, String fecha, double montoTotal, int id_cliente, int id_detalleCompra) {
        this.idCompra = id;
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        this.id_cliente = id_cliente;
        this.id_detalleCompra = id_detalleCompra;
    }

    /**
     * @return the id
     */
    public int getId() {
        return idCompra;
    }

    /**
     * @param idCompra the id to set
     */
    public void setId(int idCompra) {
        this.idCompra = idCompra;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the montoTotal
     */
    public double getMontoTotal() {
        return montoTotal;
    }

    /**
     * @param montoTotal the montoTotal to set
     */
    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    /**
     * @return the id_cliente
     */
    public int getId_cliente() {
        return id_cliente;
    }

    /**
     * @param id_cliente the id_cliente to set
     */
    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    /**
     * @return the id_detalleCompra
     */
    public int getId_detalleCompra() {
        return id_detalleCompra;
    }

    /**
     * @param id_detalleCompra the id_detalleCompra to set
     */
    public void setId_detalleCompra(int id_detalleCompra) {
        this.id_detalleCompra = id_detalleCompra;
    }

}
