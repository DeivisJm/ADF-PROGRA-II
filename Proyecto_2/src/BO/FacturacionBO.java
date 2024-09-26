package BO;

import MODEL.Facturacion;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author deivi
 */
public class FacturacionBO {

    private Facturacion facturacion;

    public FacturacionBO(Facturacion facturacion) {
        this.facturacion = facturacion;
    }

    public void Facturar(JTable tabla, JTextArea Area) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        this.TituloFactura(Area);
        int filas = modelo.getRowCount();
        double subTotal = 0;
        for (int i = 0; i < filas; i++) {
            facturacion.setNombre((String) modelo.getValueAt(i, 0));
            facturacion.setPeso((String) modelo.getValueAt(i, 1));
            facturacion.setCantidad((int) modelo.getValueAt(i, 2));
            facturacion.setPrecio((double) modelo.getValueAt(i, 3));
            subTotal += facturacion.getPrecio();
            facturacion.setSubTotal(subTotal);
            this.AñadirStringB();
        }
        this.Calculos();
        Area.append(facturacion.getFacturaProducto().toString());
    }

    public void AñadirStringB() {
        facturacion.getFacturaProducto().append(facturacion.getNombre()).append("\t  ");
        facturacion.getFacturaProducto().append("Peso:" + facturacion.getPeso()).append("\t\t");
        facturacion.getFacturaProducto().append("Cant:" + facturacion.getCantidad()).append("\t");
        facturacion.getFacturaProducto().append(facturacion.getPrecio()).append("\n");
    }

    public void TituloFactura(JTextArea Area) {
        String Mostrar = "";
        Mostrar = "Factura:\n"
                + "Nombre Cliente:\n"
                + "____________________________________________________________________________________________________________\n"
                + "Articulo\t\t \n"
                + "____________________________________________________________________________________________________________\n";
        Area.append(Mostrar);
    }

    public void Calculos() {
        facturacion.getFacturaProducto().append("_________________________________________________________________________________________________________________\n");
        facturacion.getFacturaProducto().append("                                                                                     Subtotal\t").append(facturacion.getSubTotal()).append("\n");
        facturacion.getFacturaProducto().append("                                                                                     Impuesto\t").append(facturacion.getSubTotal() / 15).append("\n");
        facturacion.getFacturaProducto().append("                                                                                     Total\t").append(facturacion.getSubTotal() + (facturacion.getSubTotal() / 15));
    }

    public void eliminarFila(JTable tabla) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        int eliminar = tabla.getSelectedRow();
        if (eliminar >= 0) {
            model.removeRow(eliminar);
        }
    }
}
