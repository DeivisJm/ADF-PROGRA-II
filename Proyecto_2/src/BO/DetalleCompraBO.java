package BO;

import DAO.DetalleCompraDAO;
import ENTITY.DetalleCompra;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author deivi
 */

public class DetalleCompraBO {

    private DetalleCompraDAO detalleCompraDAO = new DetalleCompraDAO();

    public void guardarDetalleCompra(DetalleCompra detalleCompra) {
        detalleCompraDAO.guardarDetalleCompra(detalleCompra);
    }

    public void actualizarTabla(DefaultTableModel modeloTabla) {
        detalleCompraDAO.actualizarTabla(modeloTabla);
    }

    public void editarDetalleCompra(int idDetalleCompra, int cantidad, double monto, String idProducto, int idCompra) {
        detalleCompraDAO.editarDetalleCompra(idDetalleCompra, cantidad, monto, idProducto, idCompra);
    }

    public void eliminarDetalleCompra(int idDetalleCompra) {
        detalleCompraDAO.eliminarDetalleCompra(idDetalleCompra);
    }
}
