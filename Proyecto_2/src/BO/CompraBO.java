package BO;

import DAO.CompraDAO;
import MODEL.Compra;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author deivi
 */
public class CompraBO {

    private CompraDAO compraDAO = new CompraDAO();

    public void guardarCompra(Compra compra) {
        compraDAO.guardarCompra(compra);
    }

    public void actualizarTabla(DefaultTableModel modeloTabla) {
        compraDAO.actualizarTabla(modeloTabla);
    }

    public void editarCompra(int idCompra, String fecha, double montoTotal, int idCliente, int idDetalleCompra) {
        compraDAO.editarCompra(idCompra, fecha, montoTotal, idCliente, idDetalleCompra);
    }

    public void eliminarCompra(int idCompra) {
        compraDAO.eliminarCompra(idCompra);
    }
}
