package ENTITY;

import GUI.Administrador;
import GUI.Usuario;
import DAO.DB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author deivi
 */
public class Datos {

    private Administrador tico;
    private Usuario tico2;

    public Datos() {

    }

    public Datos(Administrador tico) {
        this.tico = tico;
    }

    public Datos(Usuario tico2) {
        this.tico2 = tico2;
    }

    /* 
     * Este método es para cargar los productos desde la base de datos. 
     * Usamos una lista para almacenar los productos de forma eficiente.
     */
    public List<Producto> cargarProductos(int idCategoria) {
        ArrayList<Producto> infoProductos = new ArrayList<>();
        DB db = new DB(); // Asegúrate de que esta clase maneja la conexión a la base de datos

        String sql = "SELECT nombre, peso, precio, imagen, cantidad FROM productos WHERE id_categoria = ?";

        try (Connection connection = db.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idCategoria);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String peso = resultSet.getString("peso");
                double precio = resultSet.getDouble("precio");
                String imagen = resultSet.getString("imagen");
                int cantidad = resultSet.getInt("cantidad");

                infoProductos.add(new Producto(nombre, precio, peso, imagen, cantidad));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return infoProductos;
    }

    public List<String> obtenerCategorias() {
        List<String> categorias = new ArrayList<>();
        DB db = new DB();
        String sql = "SELECT nombre_categoria FROM categorias";

        try (Connection connection = db.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                categorias.add(resultSet.getString("nombre_categoria"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Reemplazar con un logger en un entorno de producción
        } finally {
            db.disconnect();
        }

        return categorias; // Devuelve la lista de nombres de categorías
    }

    // Método para obtener el ID de la subcategoría desde la base de datos
    public List<String> obtenerSubcategoriasPorCategoria(String nombreCategoria) {
        List<String> subcategorias = new ArrayList<>();
        DB db = new DB();
        String sql = "SELECT s.nombre_subcategoria "
                + "FROM subcategorias s "
                + "JOIN categorias c ON s.id_categoria = c.id_categoria "
                + "WHERE c.nombre_categoria = ?";

        try (Connection connection = db.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nombreCategoria);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                subcategorias.add(resultSet.getString("nombre_subcategoria"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Reemplazar con un logger en un entorno de producción
        } finally {
            db.disconnect();
        }

        return subcategorias; // Devuelve la lista de nombres de subcategorías
    }

    public List<Producto> obtenerProductosPorSubcategoria(int idSubcategoria) {
        List<Producto> productos = new ArrayList<>();
        DB db = new DB();
        String sql = "SELECT nombre, peso, precio, imagen, cantidad "
                + "FROM productos "
                + "WHERE id_subcategoria = ?";

        try (Connection connection = db.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idSubcategoria);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String peso = resultSet.getString("peso");
                double precio = resultSet.getDouble("precio");
                String imagen = resultSet.getString("imagen");
                int cantidad = resultSet.getInt("cantidad");

                // Usando el constructor de Producto que se ajusta a los campos obtenidos
                productos.add(new Producto(nombre, precio, peso, imagen, cantidad));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Reemplazar con un logger en un entorno de producción
        } finally {
            db.disconnect();
        }

        return productos; // Devuelve la lista de productos
    }

    public int obtenerIdCategoria(String nombreCategoria) {
        int idCategoria = -1; // Valor predeterminado para indicar que no se encontró la categoría
        DB db = new DB();
        String sql = "SELECT id_categoria FROM categorias WHERE nombre_categoria = ? LIMIT 1";

        try (Connection connection = db.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nombreCategoria);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                idCategoria = resultSet.getInt("id_categoria");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Reemplazar con un logger en un entorno de producción
        } finally {
            db.disconnect();
        }

        return idCategoria; // Devuelve -1 si no se encontró la categoría
    }

    public int obtenerIdSubcategoria(String nombreSubcategoria) {
        int idSubcategoria = -1; // Valor predeterminado para indicar que no se encontró la subcategoría
        DB db = new DB();
        String sql = "SELECT id_subcategoria FROM subcategorias WHERE nombre_subcategoria = ? LIMIT 1";

        try (Connection connection = db.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nombreSubcategoria);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                idSubcategoria = resultSet.getInt("id_subcategoria");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Reemplazar con un logger en un entorno de producción
        } finally {
            db.disconnect();
        }

        return idSubcategoria; // Devuelve -1 si no se encontró la subcategoría
    }

}
