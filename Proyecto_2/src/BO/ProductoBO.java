package BO;

import DAO.ProductoDAO;
import MODEL.Producto;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author deivi
 */
public class ProductoBO {

    private ProductoDAO productoDAO = new ProductoDAO();

    public void guardarProducto(String subcategoria, Producto producto) {
        // Lógica de negocio antes de guardar el producto
        productoDAO.guardarProducto(subcategoria, producto);
    }

    public void actualizarTabla(DefaultTableModel modeloTabla, String subcategoria) {
        // Lógica de negocio para actualizar la tabla de productos
        productoDAO.actualizarTabla(modeloTabla, subcategoria);
    }

    public void eliminarProducto(String subcategoria, String idProducto) {
        // Lógica de negocio para eliminar un producto
        productoDAO.eliminarProducto(subcategoria, idProducto);
    }

    public void editarProducto(String subcategoria, Producto producto) {
        // Lógica de negocio para editar un producto
        productoDAO.editarProducto(subcategoria, producto);
    }
}
