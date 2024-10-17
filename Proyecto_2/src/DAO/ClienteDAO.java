package DAO;

import MODEL.Cliente;
import com.google.gson.Gson;
import org.json.simple.*;
import org.json.simple.parser.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author deivi
 */
public class ClienteDAO {

    public void guardarCliente(Cliente cliente) {
        try {
            File archivoJSON = new File("cliente.json");
            if (!archivoJSON.exists()) {
                archivoJSON.createNewFile();
                JSONArray clientesArrayVacio = new JSONArray();
                try (FileWriter fileWriter = new FileWriter(archivoJSON)) {
                    fileWriter.write(clientesArrayVacio.toJSONString());
                }
            }

            JSONParser parser = new JSONParser();
            JSONArray clientesArray = (JSONArray) parser.parse(new FileReader(archivoJSON));

            for (Object obj : clientesArray) {
                JSONObject clienteJSON = (JSONObject) obj;
                String cedulaCliente = clienteJSON.get("cedula").toString();
                if (cedulaCliente.equals(cliente.getCedula())) {
                    System.out.println("Ya existe un cliente con la cédula " + cliente.getCedula());
                    return;
                }
            }

            JSONObject clienteJSON = new JSONObject();
            clienteJSON.put("id", cliente.getId());
            clienteJSON.put("cedula", cliente.getCedula());
            clienteJSON.put("nombre", cliente.getNombre());
            clienteJSON.put("primer_apellido", cliente.getPrimerApellido());
            clienteJSON.put("segundo_apellido", cliente.getSegundoApellido());
            clienteJSON.put("telefono", cliente.getTelefono());
            clienteJSON.put("correo", cliente.getCorreo());

            clientesArray.add(clienteJSON);

            try (FileWriter fileWriter = new FileWriter("cliente.json")) {
                fileWriter.write(clientesArray.toJSONString());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void actualizarTabla(DefaultTableModel modeloTabla) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray clientesArray = (JSONArray) parser.parse(new FileReader("cliente.json"));

            modeloTabla.setRowCount(0);

            for (Object obj : clientesArray) {
                JSONObject clienteJSON = (JSONObject) obj;
                int id = Integer.parseInt(clienteJSON.get("id").toString());
                String cedula = clienteJSON.get("cedula") != null ? clienteJSON.get("cedula").toString() : "";
                String nombre = clienteJSON.get("nombre") != null ? clienteJSON.get("nombre").toString() : "";
                String primerApellido = clienteJSON.get("primer_apellido") != null ? clienteJSON.get("primer_apellido").toString() : "";
                String segundoApellido = clienteJSON.get("segundo_apellido") != null ? clienteJSON.get("segundo_apellido").toString() : "";
                String telefono = clienteJSON.get("telefono") != null ? clienteJSON.get("telefono").toString() : "";
                String correo = clienteJSON.get("correo") != null ? clienteJSON.get("correo").toString() : "";
                modeloTabla.addRow(new Object[]{id, cedula, nombre, primerApellido, segundoApellido, telefono, correo});
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void editarCliente(int id, String cedula, String nombre, String primerApellido, String segundoApellido, String telefono, String correo) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray clientesArray = (JSONArray) parser.parse(new FileReader("cliente.json"));
            boolean clienteEncontrado = false;

            for (Object obj : clientesArray) {
                JSONObject clienteJSON = (JSONObject) obj;
                int clienteId = Integer.parseInt(clienteJSON.get("id").toString());
                if (clienteId == id) {
                    if (!cedula.isEmpty()) {
                        clienteJSON.put("cedula", cedula);
                    }
                    if (!nombre.isEmpty()) {
                        clienteJSON.put("nombre", nombre);
                    }
                    if (!primerApellido.isEmpty()) {
                        clienteJSON.put("primer_apellido", primerApellido);
                    }
                    if (!segundoApellido.isEmpty()) {
                        clienteJSON.put("segundo_apellido", segundoApellido);
                    }
                    if (!telefono.isEmpty()) {
                        clienteJSON.put("telefono", telefono);
                    }
                    if (!correo.isEmpty()) {
                        clienteJSON.put("correo", correo);
                    }
                    clienteEncontrado = true;
                    break;
                }
            }

            if (clienteEncontrado) {
                try (FileWriter fileWriter = new FileWriter("cliente.json")) {
                    fileWriter.write(clientesArray.toJSONString());
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void eliminarCliente(int id) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray clientesArray = (JSONArray) parser.parse(new FileReader("cliente.json"));

            List<Integer> indicesAEliminar = new ArrayList<>();
            boolean clienteEncontrado = false;

            for (int i = 0; i < clientesArray.size(); i++) {
                JSONObject clienteJSON = (JSONObject) clientesArray.get(i);
                int clienteId = Integer.parseInt(clienteJSON.get("id").toString());
                if (clienteId == id) {
                    indicesAEliminar.add(i);
                    clienteEncontrado = true;
                }
            }

            if (clienteEncontrado) {
                for (int i : indicesAEliminar) {
                    clientesArray.remove(i);
                }

                try (FileWriter fileWriter = new FileWriter("cliente.json")) {
                    fileWriter.write(clientesArray.toJSONString());
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public int obtenerIdClientePorNombre(String nombreCliente) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        int idCliente = -1;

        try (FileReader reader = new FileReader("cliente.json")) {
            JSONArray clientesArray = (JSONArray) parser.parse(reader);

            for (Object obj : clientesArray) {
                JSONObject cliente = (JSONObject) obj;
                String nombre = (String) cliente.get("nombre");
                if (nombreCliente.equals(nombre)) {
                    idCliente = Integer.parseInt(cliente.get("id").toString());
                    break;
                }
            }
        }

        return idCliente;
    }
    
    public void buscarClientePorNombre(String nombreBuscado, JTable tabla) {
    JSONParser parser = new JSONParser();

    try {
        // Leer el archivo cliente.json
        JSONArray clientes = (JSONArray) parser.parse(new FileReader("cliente.json"));

        // Limpiar la tabla antes de agregar el nuevo resultado
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);

        // Buscar el cliente por nombre
        boolean clienteEncontrado = false;
        for (Object clienteObj : clientes) {
            JSONObject cliente = (JSONObject) clienteObj;

            String nombre = (String) cliente.get("nombre");

            if (nombre.equalsIgnoreCase(nombreBuscado)) {
                clienteEncontrado = true;

                // Si encuentra el cliente, agregarlo a la tabla
                Object[] fila = new Object[7];  // Ajustar el tamaño según las columnas de la tabla
                fila[0] = cliente.get("id");  // ID
                fila[1] = cliente.get("cedula");
                fila[2] = cliente.get("nombre");
                fila[3] = cliente.get("primer_apellido");
                fila[4] = cliente.get("segundo_apellido");
                fila[5] = cliente.get("telefono");
                fila[6] = cliente.get("correo");

                modelo.addRow(fila);
            }
        }

        // Si no encuentra el cliente
        if (!clienteEncontrado) {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
        }

    } catch (IOException | ParseException e) {
        e.printStackTrace();
    }
}
    
    public void cargarTodosLosClientes(JTable tablaClientes) {
    try {
        // Leer el archivo JSON
        Gson gson = new Gson();
        BufferedReader reader = new BufferedReader(new FileReader("cliente.json"));
        Cliente[] clientes = gson.fromJson(reader, Cliente[].class);

        // Obtener el modelo de la tabla
        DefaultTableModel model = (DefaultTableModel) tablaClientes.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de cargar nuevos datos

        // Añadir cada cliente al modelo de la tabla
        for (Cliente cliente : clientes) {
            Object[] fila = {
                cliente.getId(),
                cliente.getCedula(),
                cliente.getNombre(),
                cliente.getPrimerApellido(),
                cliente.getSegundoApellido(),
                cliente.getTelefono(),
                cliente.getCorreo()
            };
            model.addRow(fila);
        }
        reader.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}


}
