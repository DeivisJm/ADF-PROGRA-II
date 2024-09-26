package BO;

import DAO.ProveedorDAO;
import MODEL.Proveedor;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author deivi
 */
public class ProveedorBO {

    private ProveedorDAO proveedorDAO = new ProveedorDAO();

    public void guardarProveedor(Proveedor proveedor) throws IOException, ParseException {
        JSONArray proveedorArray = proveedorDAO.leerProveedores();

        for (Object obj : proveedorArray) {
            JSONObject proveedorJSON = (JSONObject) obj;
            String cedulaProveedor = proveedorJSON.get("cedula").toString();
            if (cedulaProveedor.equals(proveedor.getCedulaJuridica())) {
                System.out.println("Ya existe un cliente con la cédula " + proveedor.getCedulaJuridica());
                return;
            }
        }

        proveedorDAO.guardarProveedor(proveedor);
    }

    public void actualizarTabla(DefaultTableModel modeloTabla) throws IOException, ParseException {
        JSONArray proveedorArray = proveedorDAO.leerProveedores();
        modeloTabla.setRowCount(0);

        for (Object obj : proveedorArray) {
            JSONObject proveedorJSON = (JSONObject) obj;
            int Id = Integer.parseInt(proveedorJSON.get("id").toString());
            if (Id != 0) {
                String Cedula = proveedorJSON.get("cedula") != null ? proveedorJSON.get("cedula").toString() : "";
                String Nombre = proveedorJSON.get("nombre") != null ? proveedorJSON.get("nombre").toString() : "";
                String Telefono = proveedorJSON.get("telefono") != null ? proveedorJSON.get("telefono").toString() : "";
                String Correo = proveedorJSON.get("correo") != null ? proveedorJSON.get("correo").toString() : "";
                modeloTabla.addRow(new Object[]{Id, Cedula, Nombre, Telefono, Correo});
            }
        }
    }

    public void editarProveedor(Proveedor proveedor) throws IOException, ParseException {
        JSONArray proveedoresArray = proveedorDAO.leerProveedores();
        boolean proveedorEncontrado = false;

        for (int i = 0; i < proveedoresArray.size(); i++) {
            JSONObject proveedorJSON = (JSONObject) proveedoresArray.get(i);
            int proveedorId = Integer.parseInt(proveedorJSON.get("id").toString());

            if (proveedorId == proveedor.getId()) {
                if (!proveedor.getCedulaJuridica().isEmpty()) {
                    proveedorJSON.put("cedula", proveedor.getCedulaJuridica());
                }
                if (!proveedor.getNombre().isEmpty()) {
                    proveedorJSON.put("nombre", proveedor.getNombre());
                }
                if (!proveedor.getTelefono().isEmpty()) {
                    proveedorJSON.put("telefono", proveedor.getTelefono());
                }
                if (!proveedor.getCorreo().isEmpty()) {
                    proveedorJSON.put("correo", proveedor.getCorreo());
                }

                proveedorEncontrado = true;
                break;
            }
        }

        if (proveedorEncontrado) {
            proveedorDAO.actualizarProveedores(proveedoresArray);
        }
    }

    public void eliminarProveedor(int id) throws IOException, ParseException {
        // Llamar al método DAO para eliminar el proveedor por ID
        proveedorDAO.eliminarProveedor(id);
    }

}
