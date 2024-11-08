package DAO;

import ENTITY.Cliente;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class ClienteDAO {

    private Connection connection;

    public ClienteDAO() {
        connection = new DB().getConnection(); // Asegúrate de que la conexión se inicializa aquí
    }
// Asegúrate de que la conexión se inicializa aquí

    public void guardarCliente(Cliente cliente) {
        String verificarSql = "SELECT COUNT(*) FROM clientes WHERE cedula = ?";
        String sql = "INSERT INTO clientes (cedula, nombre, primer_apellido, segundo_apellido, telefono, correo) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement verificarStmt = connection.prepareStatement(verificarSql)) {
            // Verificar si la cédula ya existe en la base de datos
            verificarStmt.setString(1, cliente.getCedula());
            ResultSet resultSet = verificarStmt.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count > 0) {
                JOptionPane.showMessageDialog(null, "El cliente con la cédula " + cliente.getCedula() + " ya existe.");
                return; // Termina el método para evitar la inserción de un cliente duplicado
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, cliente.getCedula());
                preparedStatement.setString(2, cliente.getNombre());
                preparedStatement.setString(3, cliente.getPrimerApellido());
                preparedStatement.setString(4, cliente.getSegundoApellido());
                preparedStatement.setString(5, cliente.getTelefono());
                preparedStatement.setString(6, cliente.getCorreo());

                preparedStatement.executeUpdate();
                System.out.println("Cliente guardado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar el cliente: " + e.getMessage());
        }
    }

    public void actualizarTabla(DefaultTableModel modeloTabla) {
        String sql = "SELECT * FROM clientes";

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            modeloTabla.setRowCount(0); // Limpiar la tabla

            while (resultSet.next()) {
                int id = resultSet.getInt("id_cliente");
                String cedula = resultSet.getString("cedula");
                String nombre = resultSet.getString("nombre");
                String primerApellido = resultSet.getString("primer_apellido");
                String segundoApellido = resultSet.getString("segundo_apellido");
                String telefono = resultSet.getString("telefono");
                String correo = resultSet.getString("correo");

                modeloTabla.addRow(new Object[]{id, cedula, nombre, primerApellido, segundoApellido, telefono, correo});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarCliente(int id, String cedula, String nombre, String primerApellido, String segundoApellido, String telefono, String correo) {
        String sql = "UPDATE clientes SET cedula = ?, nombre = ?, primer_apellido = ?, segundo_apellido = ?, telefono = ?, correo = ? WHERE id_cliente = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cedula);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, primerApellido);
            preparedStatement.setString(4, segundoApellido);
            preparedStatement.setString(5, telefono);
            preparedStatement.setString(6, correo);
            preparedStatement.setInt(7, id);

            preparedStatement.executeUpdate();
            System.out.println("Cliente actualizado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar el cliente: " + e.getMessage());
        }
    }

    public void eliminarCliente(int id) {
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Cliente eliminado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar el cliente: " + e.getMessage());
        }
    }

    public int obtenerIdClientePorNombre(String nombreCliente) {
        String sql = "SELECT id_cliente FROM clientes WHERE nombre = ?";
        int idCliente = -1;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nombreCliente);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                idCliente = resultSet.getInt("id_cliente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idCliente;
    }

    public void buscarClientePorNombre(String nombreBuscado, JTable tabla) {
        String sql = "SELECT * FROM clientes WHERE nombre LIKE ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + nombreBuscado + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0); // Limpiar la tabla

            boolean clienteEncontrado = false;
            while (resultSet.next()) {
                clienteEncontrado = true;
                Object[] fila = new Object[7]; // Ajustar el tamaño según las columnas de la tabla
                fila[0] = resultSet.getInt("id_cliente");  // ID
                fila[1] = resultSet.getString("cedula");
                fila[2] = resultSet.getString("nombre");
                fila[3] = resultSet.getString("primer_apellido");
                fila[4] = resultSet.getString("segundo_apellido");
                fila[5] = resultSet.getString("telefono");
                fila[6] = resultSet.getString("correo");
                modelo.addRow(fila);
            }

            if (!clienteEncontrado) {
                JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cargarTodosLosClientes(JTable tablaClientes) {
        String sql = "SELECT * FROM clientes";

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            DefaultTableModel model = (DefaultTableModel) tablaClientes.getModel();
            model.setRowCount(0); // Limpiar la tabla antes de cargar nuevos datos

            while (resultSet.next()) {
                Object[] fila = {
                    resultSet.getInt("id_cliente"),
                    resultSet.getString("cedula"),
                    resultSet.getString("nombre"),
                    resultSet.getString("primer_apellido"),
                    resultSet.getString("segundo_apellido"),
                    resultSet.getString("telefono"),
                    resultSet.getString("correo")
                };
                model.addRow(fila);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
