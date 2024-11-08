package DAO;

import ENTITY.Compra;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author deivi
 */
public class CompraDAO {

    public void guardarCompra(Compra compra) {
        DB db = new DB();
        String sql = "INSERT INTO compras (fecha, monto_total, id_cliente, id_detalle_compra) VALUES (?, ?, ?, ?)";

        try (Connection connection = db.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, compra.getFecha());
            preparedStatement.setDouble(2, compra.getMontoTotal());
            preparedStatement.setInt(3, compra.getId_cliente());
            preparedStatement.setInt(4, compra.getId_detalleCompra());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Reemplazar con un logger en un entorno de producción
        }
    }

    public void actualizarTabla(DefaultTableModel modeloTabla) {
        DB db = new DB();
        String sql = "SELECT * FROM compras";

        try (Connection connection = db.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql); ResultSet resultSet = preparedStatement.executeQuery()) {

            modeloTabla.setRowCount(0); // Limpiar la tabla antes de actualizar

            while (resultSet.next()) {
                int idCompra = resultSet.getInt("id_compra");
                String fecha = resultSet.getString("fecha");
                double montoTotal = resultSet.getDouble("monto_total");
                int idCliente = resultSet.getInt("id_cliente");
                int idDetalleCompra = resultSet.getInt("id_detalle_compra");

                modeloTabla.addRow(new Object[]{idCompra, fecha, montoTotal, idCliente, idDetalleCompra});
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Reemplazar con un logger en un entorno de producción
        }
    }

    public void editarCompra(int idCompra, String fecha, double montoTotal, int idCliente, int idDetalleCompra) {
        DB db = new DB();
        String sql = "UPDATE compras SET fecha = ?, monto_total = ?, id_cliente = ?, id_detalle_compra = ? WHERE id_compra = ?";

        try (Connection connection = db.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, fecha);
            preparedStatement.setDouble(2, montoTotal);
            preparedStatement.setInt(3, idCliente);
            preparedStatement.setInt(4, idDetalleCompra);
            preparedStatement.setInt(5, idCompra);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("No se encontró una compra con el ID " + idCompra);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Reemplazar con un logger en un entorno de producción
        }
    }

    public void eliminarCompra(int idCompra) {
        DB db = new DB();
        String sql = "DELETE FROM compras WHERE id_compra = ?";

        try (Connection connection = db.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idCompra);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted == 0) {
                System.out.println("No se encontró una compra con el ID " + idCompra);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Reemplazar con un logger en un entorno de producción
        }
    }

    // Este método es opcional y puede requerir modificaciones adicionales para trabajar con la base de datos
    public String obtenerIdProductoPorNombreYSubcategoria(String nombreProducto, String subCategoria) {
        DB db = new DB();
        String sql = "SELECT id_producto FROM productos WHERE nombre = ? AND id_subcategoria = (SELECT id_subcategoria FROM subcategorias WHERE nombre = ?)";

        try (Connection connection = db.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nombreProducto);
            preparedStatement.setString(2, subCategoria);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return String.valueOf(resultSet.getInt("id_producto"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Reemplazar con un logger en un entorno de producción
        }

        return null;
    }
}
