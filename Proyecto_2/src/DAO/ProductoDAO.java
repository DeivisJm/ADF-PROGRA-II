package DAO;

import ENTITY.Producto;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author deivi
 */
public class ProductoDAO {

    public void guardarProducto(String subcategoria, Producto producto) {
        DB db = new DB();
        String sql = "INSERT INTO productos (nombre, precio, peso, id_proveedor, id_categoria, marca, imagen, cantidad) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = db.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, producto.getNombre());
            preparedStatement.setDouble(2, producto.getPrecio());
            preparedStatement.setString(3, producto.getPeso());
            preparedStatement.setInt(4, Integer.parseInt(producto.getIdProveedor()));
            preparedStatement.setInt(5, Integer.parseInt(producto.getIdCategoria()));
            preparedStatement.setInt(6, Integer.parseInt(producto.getIdMarca()));
            preparedStatement.setString(7, producto.getImagen());
            preparedStatement.setInt(8, producto.getCantidad());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // En producción, reemplazar con un logger
        }
    }

    public void actualizarTabla(DefaultTableModel modeloTabla, String subcategoria) {
        DB db = new DB();
        int idSubcategoria;

        try {
            idSubcategoria = Integer.parseInt(subcategoria);
        } catch (NumberFormatException e) {
            idSubcategoria = obtenerIdSubcategoriaPorNombre(subcategoria);
        }

        if (idSubcategoria == -1) {
            System.out.println("La subcategoría no se encontró.");
            return;
        }

        String sql = "SELECT * FROM productos WHERE id_subcategoria = ?";
        try (Connection connection = db.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idSubcategoria);
            ResultSet resultSet = preparedStatement.executeQuery();
            modeloTabla.setRowCount(0); // Limpiar la tabla antes de actualizar

            while (resultSet.next()) {
                int id = resultSet.getInt("id_producto");
                String nombre = resultSet.getString("nombre");
                double precio = resultSet.getDouble("precio");
                String peso = resultSet.getString("peso");
                int idProveedor = resultSet.getInt("id_proveedor");
                int idCategoria = resultSet.getInt("id_categoria");
                int idMarca = resultSet.getInt("marca");
                int cantidad = resultSet.getInt("cantidad");

                modeloTabla.addRow(new Object[]{id, nombre, precio, peso, idProveedor, idCategoria, idMarca, cantidad});
            }
        } catch (SQLException e) {
            e.printStackTrace(); // En producción, reemplazar con un logger
        }
    }

    public void eliminarProducto(String subcategoria, String idProducto) {
        DB db = new DB();
        String sql = "DELETE FROM productos WHERE id_producto = ? AND id_subcategoria = ?";

        try (Connection connection = db.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, Integer.parseInt(idProducto));
            preparedStatement.setInt(2, Integer.parseInt(subcategoria));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // En producción, reemplazar con un logger
        }
    }

    public void editarProducto(String subcategoria, Producto producto) {
        DB db = new DB();
        String sql = "UPDATE productos SET nombre = ?, precio = ?, peso = ?, id_proveedor = ?, id_categoria = ?, marca = ?, imagen = ?, cantidad = ? WHERE id_producto = ? AND id_subcategoria = ?";

        try (Connection connection = db.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, producto.getNombre());
            preparedStatement.setDouble(2, producto.getPrecio());
            preparedStatement.setString(3, producto.getPeso());
            preparedStatement.setInt(4, Integer.parseInt(producto.getIdProveedor()));
            preparedStatement.setInt(5, Integer.parseInt(producto.getIdCategoria()));
            preparedStatement.setInt(6, Integer.parseInt(producto.getIdMarca()));
            preparedStatement.setString(7, producto.getImagen());
            preparedStatement.setInt(8, producto.getCantidad());
            preparedStatement.setInt(9, Integer.parseInt(producto.getIdProducto()));
            preparedStatement.setInt(10, Integer.parseInt(subcategoria));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // En producción, reemplazar con un logger
        }
    }

    public int obtenerIdSubcategoriaPorNombre(String nombreSubcategoria) {
        DB db = new DB();
        String sql = "SELECT id_subcategoria FROM subcategorias WHERE nombre_subcategoria = ?";
        int idSubcategoria = -1;

        try (Connection connection = db.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nombreSubcategoria);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                idSubcategoria = resultSet.getInt("id_subcategoria");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // En producción, usar un logger
        }

        return idSubcategoria;
    }

}
