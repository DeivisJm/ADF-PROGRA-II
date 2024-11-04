package DAO;

import ENTITY.DetalleCompra;
import org.json.simple.*;
import org.json.simple.parser.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author deivi
 */
public class DetalleCompraDAO {

    public void guardarDetalleCompra(DetalleCompra detalleCompra) {
        try {
            File archivoJSON = new File("DetalleCompra.json");

            JSONParser parser = new JSONParser();
            JSONArray detalleCompraArray = (JSONArray) parser.parse(new FileReader(archivoJSON));

            JSONObject detalleCompraJSON = new JSONObject();
            detalleCompraJSON.put("id", detalleCompra.getId_DetalleCompra());
            detalleCompraJSON.put("cantidad", detalleCompra.getCantidad());
            detalleCompraJSON.put("monto", detalleCompra.getMonto());
            detalleCompraJSON.put("idProducto", detalleCompra.getIdProducto());
            detalleCompraJSON.put("idCompra", detalleCompra.getIdCompra());

            detalleCompraArray.add(detalleCompraJSON);

            try (FileWriter fileWriter = new FileWriter("DetalleCompra.json")) {
                fileWriter.write(detalleCompraArray.toJSONString());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void actualizarTabla(DefaultTableModel modeloTabla) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray detalleCompraArray = (JSONArray) parser.parse(new FileReader("DetalleCompra.json"));

            modeloTabla.setRowCount(0);

            for (Object obj : detalleCompraArray) {
                JSONObject detalleCompraJSON = (JSONObject) obj;
                int idDetalleCompra = Integer.parseInt(detalleCompraJSON.get("id").toString());
                if (idDetalleCompra != 0) {
                    int cantidad = Integer.parseInt(detalleCompraJSON.get("cantidad").toString());
                    double monto = Double.parseDouble(detalleCompraJSON.getOrDefault("monto", "0").toString());
                    String idProducto = detalleCompraJSON.getOrDefault("idProducto", "").toString();
                    int idCompra = Integer.parseInt(detalleCompraJSON.get("idCompra").toString());
                    modeloTabla.addRow(new Object[]{idDetalleCompra, cantidad, monto, idProducto, idCompra});
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void editarDetalleCompra(int idDetalleCompra, int cantidad, double monto, String idProducto, int idCompra) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray detalleCompraArray = (JSONArray) parser.parse(new FileReader("DetalleCompra.json"));

            for (Object obj : detalleCompraArray) {
                JSONObject detalleCompraJSON = (JSONObject) obj;
                int detalleId = Integer.parseInt(detalleCompraJSON.get("id").toString());
                if (detalleId == idDetalleCompra) {
                    if (cantidad >= 0) {
                        detalleCompraJSON.put("cantidad", cantidad);
                    }
                    if (monto >= 0) {
                        detalleCompraJSON.put("monto", monto);
                    }
                    if (idProducto != null) {
                        detalleCompraJSON.put("idProducto", idProducto);
                    }
                    if (idCompra != 0) {
                        detalleCompraJSON.put("idCompra", idCompra);
                    }
                    break;
                }
            }

            try (FileWriter fileWriter = new FileWriter("DetalleCompra.json")) {
                fileWriter.write(detalleCompraArray.toJSONString());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void eliminarDetalleCompra(int idDetalleCompra) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray detalleCompraArray = (JSONArray) parser.parse(new FileReader("DetalleCompra.json"));

            List<Integer> indicesAEliminar = new ArrayList<>();
            boolean detalleEncontrado = false;
            for (int i = 0; i < detalleCompraArray.size(); i++) {
                JSONObject detalleCompraJSON = (JSONObject) detalleCompraArray.get(i);
                int detalleId = Integer.parseInt(detalleCompraJSON.get("id").toString());
                if (detalleId == idDetalleCompra) {
                    indicesAEliminar.add(i);
                    detalleEncontrado = true;
                }
            }

            if (detalleEncontrado) {
                for (int i : indicesAEliminar) {
                    detalleCompraArray.remove(i);
                }

                try (FileWriter fileWriter = new FileWriter("DetalleCompra.json")) {
                    fileWriter.write(detalleCompraArray.toJSONString());
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
