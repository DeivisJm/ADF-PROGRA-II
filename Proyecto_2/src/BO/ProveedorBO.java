package BO;

import DAO.ProveedorDAO;
import ENTITY.Proveedor;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author andrew
 */
public class ProveedorBO {

    private ProveedorDAO proveedorDAO = new ProveedorDAO();

    public void guardarProveedor(Proveedor proveedor) {
        // Verifica si ya existe un proveedor con la misma cédula en la base de datos
        List<Proveedor> proveedores = proveedorDAO.leerProveedores();

        for (Proveedor p : proveedores) {
            if (p.getCedulaJuridica().equals(proveedor.getCedulaJuridica())) {
                System.out.println("Ya existe un cliente con la cédula " + proveedor.getCedulaJuridica());
                return;
            }
        }

        // Guarda el proveedor en la base de datos
        proveedorDAO.guardarProveedor(proveedor);
    }

    public void actualizarTabla(DefaultTableModel modeloTabla) {
        // Obtener la lista de proveedores desde el DAO
        List<Proveedor> proveedores = proveedorDAO.leerProveedores();

        // Limpiar la tabla antes de agregar los nuevos datos
        modeloTabla.setRowCount(0);

        // Agregar los proveedores al modelo de la tabla
        for (Proveedor proveedor : proveedores) {
            modeloTabla.addRow(new Object[]{
                proveedor.getId(),
                proveedor.getCedulaJuridica(),
                proveedor.getNombre(),
                proveedor.getTelefono(),
                proveedor.getCorreo()
            });
        }
    }

    public void editarProveedor(Proveedor proveedor) {
        // Verifica si el proveedor existe en la base de datos
        List<Proveedor> proveedores = proveedorDAO.leerProveedores();
        boolean proveedorEncontrado = false;

        for (Proveedor p : proveedores) {
            if (p.getId() == proveedor.getId()) {
                proveedorEncontrado = true;
                break;
            }
        }

        // Si el proveedor se encuentra, actualiza la información
        if (proveedorEncontrado) {
            proveedorDAO.actualizarProveedor(proveedor);
        } else {
            System.out.println("No se encontró un proveedor con el ID " + proveedor.getId());
        }
    }

    public void eliminarProveedor(int id) {
        // Llama al método DAO para eliminar el proveedor por ID
        proveedorDAO.eliminarProveedor(id);
    }
}
