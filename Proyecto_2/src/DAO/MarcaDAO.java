package DAO;

import ENTITY.Marca;
import org.json.simple.*;
import org.json.simple.parser.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

/**
 *
 * @author andrew
 */
public class MarcaDAO {

    public void guardarMarca(Marca marca) {
        try {
            File archivoJSON = new File("marca.json");
            if (!archivoJSON.exists()) {
                archivoJSON.createNewFile();
                JSONArray marcasArrayVacio = new JSONArray();
                try (FileWriter fileWriter = new FileWriter(archivoJSON)) {
                    fileWriter.write(marcasArrayVacio.toJSONString());
                }
            }

            JSONParser parser = new JSONParser();
            JSONArray marcasArray = (JSONArray) parser.parse(new FileReader(archivoJSON));

            for (Object obj : marcasArray) {
                JSONObject marcaJSON = (JSONObject) obj;
                String nombreMarca = marcaJSON.get("nombre").toString();
                if (nombreMarca.equals(marca.getNombre())) {
                    System.out.println("Ya existe una marca con el nombre " + marca.getNombre());
                    return;
                }
            }

            JSONObject marcaJSON = new JSONObject();
            marcaJSON.put("id", marca.getId());
            marcaJSON.put("nombre", marca.getNombre());
            marcasArray.add(marcaJSON);

            try (FileWriter fileWriter = new FileWriter("marca.json")) {
                fileWriter.write(marcasArray.toJSONString());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void actualizarTabla(DefaultTableModel modeloTabla) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray marcasArray = (JSONArray) parser.parse(new FileReader("marca.json"));

            modeloTabla.setRowCount(0);

            for (Object obj : marcasArray) {
                JSONObject marcaJSON = (JSONObject) obj;
                int Id = Integer.parseInt(marcaJSON.get("id").toString());
                if (Id != 0) {
                    String Nombre = marcaJSON.get("nombre") != null ? marcaJSON.get("nombre").toString() : "";
                    modeloTabla.addRow(new Object[]{Id, Nombre});
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void editarMarca(int id, String nombre) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray marcasArray = (JSONArray) parser.parse(new FileReader("marca.json"));
            boolean marcaEncontrada = false;

            for (int i = 0; i < marcasArray.size(); i++) {
                JSONObject marcaJSON = (JSONObject) marcasArray.get(i);
                int marcaId = Integer.parseInt(marcaJSON.get("id").toString());

                if (marcaId == id) {
                    if (!nombre.isEmpty()) {
                        marcaJSON.put("nombre", nombre);
                    }
                    marcaEncontrada = true;
                    break;
                }
            }

            if (marcaEncontrada) {
                try (FileWriter fileWriter = new FileWriter("marca.json")) {
                    fileWriter.write(marcasArray.toJSONString());
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
