package DAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.*;
import org.json.simple.parser.*;
import ENTITY.Proveedor;

/**
 *
 * @author fabri
 */
public class ProveedorDAO {

    public JSONArray leerProveedores() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        // Lee el archivo JSON y devuelve un JSONArray con los proveedores
        return (JSONArray) parser.parse(new FileReader("proveedor.json"));
    }

    public void guardarProveedor(Proveedor proveedor) throws IOException, ParseException {
        File archivoJSON = new File("proveedor.json");
        JSONParser parser = new JSONParser();
        JSONArray proveedorArray = (JSONArray) parser.parse(new FileReader(archivoJSON));

        JSONObject proveedorJSON = new JSONObject();
        proveedorJSON.put("id", proveedor.getId());
        proveedorJSON.put("cedula", proveedor.getCedulaJuridica());
        proveedorJSON.put("nombre", proveedor.getNombre());
        proveedorJSON.put("telefono", proveedor.getTelefono());
        proveedorJSON.put("correo", proveedor.getCorreo());

        proveedorArray.add(proveedorJSON);

        FileWriter fileWriter = new FileWriter("proveedor.json");
        fileWriter.write(proveedorArray.toJSONString());
        fileWriter.flush();
        fileWriter.close();
    }

    public void actualizarProveedores(JSONArray proveedorArray) throws IOException {
        // Abre el archivo JSON para escribir los datos actualizados
        FileWriter fileWriter = new FileWriter("proveedor.json");

        // Escribe el JSONArray actualizado en el archivo
        fileWriter.write(proveedorArray.toJSONString());

        // Limpia y cierra el archivo
        fileWriter.flush();
        fileWriter.close();
    }

    public void eliminarProveedor(int id) throws IOException, ParseException {
        // Leer el archivo JSON existente
        JSONParser parser = new JSONParser();
        JSONArray proveedorArray = (JSONArray) parser.parse(new FileReader("proveedor.json"));

        List<Integer> indicesAEliminar = new ArrayList<>();
        boolean proveedorEncontrado = false;

        // Buscar el proveedor con el ID especificado y marcarlo para eliminar
        for (int i = 0; i < proveedorArray.size(); i++) {
            JSONObject proveedorJSON = (JSONObject) proveedorArray.get(i);
            int proveedorId = Integer.parseInt(proveedorJSON.get("id").toString());

            if (proveedorId == id) {
                indicesAEliminar.add(i);
                proveedorEncontrado = true;
            }
        }

        // Eliminar los proveedores marcados del JSONArray
        if (proveedorEncontrado) {
            for (int i : indicesAEliminar) {
                proveedorArray.remove(i);
            }
            // Actualizar el archivo JSON con la lista modificada
            actualizarProveedores(proveedorArray);
        }
    }

}
