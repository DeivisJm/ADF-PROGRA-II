package DAO;

import ENTITY.Marca;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

/**
 *
 * @author andrew
 */
public class MarcaDAO {

    public void guardarMarca(Marca marca) {
        DB db = new DB();
        String sql = "INSERT INTO marcas (id, nombre) VALUES (?, ?)";

        try (Connection connection = db.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, marca.getId());
            preparedStatement.setString(2, marca.getNombre());

            // Verificar si la marca ya existe en la base de datos
            if (marcaExiste(connection, marca.getNombre())) {
                System.out.println("Ya existe una marca con el nombre " + marca.getNombre());
                return;
            }

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Reemplazar con un logger en un entorno de producción
        }
    }

    public void actualizarTabla(DefaultTableModel modeloTabla) {
        DB db = new DB();
        String sql = "SELECT id, nombre FROM marcas";

        try (Connection connection = db.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            modeloTabla.setRowCount(0); // Limpiar la tabla antes de actualizar

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                modeloTabla.addRow(new Object[]{id, nombre});
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Reemplazar con un logger en un entorno de producción
        }
    }

    public void editarMarca(int id, String nombre) {
        DB db = new DB();
        String sql = "UPDATE marcas SET nombre = ? WHERE id = ?";

        try (Connection connection = db.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setInt(2, id);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("No se encontró una marca con el ID " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Reemplazar con un logger en un entorno de producción
        }
    }

    // Método auxiliar para verificar si una marca ya existe en la base de datos
    private boolean marcaExiste(Connection connection, String nombre) throws SQLException {
        String sql = "SELECT 1 FROM marcas WHERE nombre = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nombre);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }
}
