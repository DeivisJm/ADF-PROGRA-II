package BO;

import DAO.ClienteDAO;
import MODEL.Cliente;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author deivi
 */
public class ClienteBO {

    private ClienteDAO clienteDAO = new ClienteDAO();

    public void guardarCliente(Cliente cliente) {
        clienteDAO.guardarCliente(cliente);
    }

    public void actualizarTabla(DefaultTableModel modeloTabla) {
        clienteDAO.actualizarTabla(modeloTabla);
    }

    public void editarCliente(int id, String cedula, String nombre, String primerApellido, String segundoApellido, String telefono, String correo) {
        clienteDAO.editarCliente(id, cedula, nombre, primerApellido, segundoApellido, telefono, correo);
    }

    public void eliminarCliente(int id) {
        clienteDAO.eliminarCliente(id);
    }
}
