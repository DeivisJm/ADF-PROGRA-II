package DAO;

import ENTITY.Proveedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabri
 */
public class ProveedorDAO {

    public List<Proveedor> leerProveedores() {
        List<Proveedor> proveedores = new ArrayList<>();
        DB db = new DB();

        try (Connection connection = db.getConnection()) {
            // Consulta SQL para seleccionar todos los proveedores
            String sql = "SELECT * FROM proveedor";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String cedulaJuridica = resultSet.getString("cedula_juridica");
                String nombre = resultSet.getString("nombre");
                String telefono = resultSet.getString("telefono");
                String correo = resultSet.getString("correo");

                Proveedor proveedor = new Proveedor(id, cedulaJuridica, nombre, telefono, correo);
                proveedores.add(proveedor);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Deberías usar un logger en un entorno de producción
        }

        return proveedores;
    }

    public void guardarProveedor(Proveedor proveedor) {
        DB db = new DB();

        try (Connection connection = db.getConnection()) {
            // Consulta SQL para insertar un nuevo proveedor
            String sql = "INSERT INTO proveedor (id, cedula_juridica, nombre, telefono, correo) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, proveedor.getId());
            preparedStatement.setString(2, proveedor.getCedulaJuridica());
            preparedStatement.setString(3, proveedor.getNombre());
            preparedStatement.setString(4, proveedor.getTelefono());
            preparedStatement.setString(5, proveedor.getCorreo());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Deberías usar un logger en un entorno de producción
        }
    }

    public void actualizarProveedor(Proveedor proveedor) {
        DB db = new DB();

        try (Connection connection = db.getConnection()) {
            // Consulta SQL para actualizar un proveedor existente
            String sql = "UPDATE proveedor SET cedula_juridica = ?, nombre = ?, telefono = ?, correo = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, proveedor.getCedulaJuridica());
            preparedStatement.setString(2, proveedor.getNombre());
            preparedStatement.setString(3, proveedor.getTelefono());
            preparedStatement.setString(4, proveedor.getCorreo());
            preparedStatement.setInt(5, proveedor.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Deberías usar un logger en un entorno de producción
        }
    }

    public void eliminarProveedor(int id) {
        DB db = new DB();

        try (Connection connection = db.getConnection()) {
            // Consulta SQL para eliminar un proveedor por ID
            String sql = "DELETE FROM proveedor WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Deberías usar un logger en un entorno de producción
        }
    }
}
