package DAO;

import ENTITY.DetalleCompra;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author deivi
 */
public class DetalleCompraDAO {

    public void guardarDetalleCompra(DetalleCompra detalleCompra) {
        DB db = new DB();
        String sql = "INSERT INTO detalle_compra (id_compra, id_producto, cantidad, monto) VALUES (?, ?, ?, ?)";

        try (Connection connection = db.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, detalleCompra.getIdCompra());
            preparedStatement.setInt(2, Integer.parseInt(detalleCompra.getIdProducto())); // Conversión de String a int
            preparedStatement.setInt(3, detalleCompra.getCantidad());
            preparedStatement.setDouble(4, detalleCompra.getMonto());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Reemplazar con un logger en un entorno de producción
        }
    }

    public void actualizarTabla(DefaultTableModel modeloTabla) {
        DB db = new DB();
        String sql = "SELECT * FROM detalle_compra";

        try (Connection connection = db.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql); ResultSet resultSet = preparedStatement.executeQuery()) {

            modeloTabla.setRowCount(0); // Limpiar la tabla antes de actualizar

            while (resultSet.next()) {
                int idDetalleCompra = resultSet.getInt("id_detalle_compra");
                int idCompra = resultSet.getInt("id_compra");
                int idProducto = resultSet.getInt("id_producto");
                int cantidad = resultSet.getInt("cantidad");
                double monto = resultSet.getDouble("monto");

                modeloTabla.addRow(new Object[]{idDetalleCompra, cantidad, monto, idProducto, idCompra});
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Reemplazar con un logger en un entorno de producción
        }
    }

    public void editarDetalleCompra(int idDetalleCompra, int cantidad, double monto, String idProducto, int idCompra) {
        DB db = new DB();
        String sql = "UPDATE detalle_compra SET cantidad = ?, monto = ?, id_producto = ?, id_compra = ? WHERE id_detalle_compra = ?";

        try (Connection connection = db.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, cantidad);
            preparedStatement.setDouble(2, monto);
            preparedStatement.setInt(3, Integer.parseInt(idProducto)); // Conversión de String a int
            preparedStatement.setInt(4, idCompra);
            preparedStatement.setInt(5, idDetalleCompra);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("No se encontró un detalle de compra con el ID " + idDetalleCompra);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Reemplazar con un logger en un entorno de producción
        }
    }

    public void eliminarDetalleCompra(int idDetalleCompra) {
        DB db = new DB();
        String sql = "DELETE FROM detalle_compra WHERE id_detalle_compra = ?";

        try (Connection connection = db.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idDetalleCompra);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted == 0) {
                System.out.println("No se encontró un detalle de compra con el ID " + idDetalleCompra);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Reemplazar con un logger en un entorno de producción
        }
    }
}
